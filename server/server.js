'use strict'

const express = require('express')
const logger = require('morgan')
const bodyParser = require('body-parser')
const mongoose = require('mongoose')
const multer = require('multer')
const mkdirp = require('mkdirp')
const storage = multer.diskStorage({
  destination: function(req, file, cb) {
    const directory = 'uploads/' + req.params.id + '/';
    mkdirp(directory, function(err) {
      if (err) console.error(err)
      else cb(null, directory)
    });

  },
  filename: function(req, file, cb) {
    cb(null, Date.now() + ' - ' + file.originalname);
  }
})

const upload = multer({
  storage: storage
})

const User = require('./models/user')
const Project = require('./models/project')

const PORT = process.env.PORT_JAVA || 3045
const DBUrl = require('./config/db').url
mongoose.connect(DBUrl)

let app = express()
let http = require('http').Server(app)
let io = require('socket.io')(http)

app.use('/uploads', express.static('uploads'));

app
  .use(logger('dev'))
  .use(bodyParser.urlencoded({
    extended: true
  }))
  .use(bodyParser.json())


/*
 * User routes
 */

// Adding a user
app.post('/user', (req, res) => {
  // 1 : login déjà pris      OK : créé avec succès
  let email = req.body.email
  let password = req.body.password

  console.log(password)

  User.findOne({
    'email': email
  }, (err, user) => {
    if (err) return res.status(500).send('Erreur !')
    if (user) {
      console.log('user exists :(')
      return res.status(500).send('1')
    }

    let newUser = new User()
    newUser.email = email
    newUser.password = password

    newUser.save((err) => {
      if (err) res.status(500).send('Erreur!')
      return res.send('OK')
    })
  })
})


// Connecting a user
app.post('/user/:email', (req, res) => {
  // -1 : mdp incorrect   0 : email inexistant    1 : connexion réussite
  let email = req.params.email
  let password = req.body.password

  User.findOne({
    'email': email
  }, (err, user) => {
    if (err) return res.status(500).send('Erreur !')
    if (user) {
      if (user.password == password) return res.status(200).send('1') // connected
      else return res.status(500).send('-1') // wrong password
    } else {
      // can't find user
      return res.status(500).send('0')
    }

  })
})

// Getting projects belonging to a certain user
app.get('/user/:email/projects', (req, res) => {
  User.findOne({
    'email': req.params.email
  }, (err, user) => {
    if (err) return res.status(500).send('Erreur !')
    let projectsIds = user.projects
    Project.find({
      '_id': {
        $in: projectsIds
      }
    }, '-users -tasks -conversation -calendar -files -__v', (err, projects) => {
      if (err) return res.status(500).send('Erreur !')
      res.send(projects)
    })
  })
})

/*
 * Project routes
 */

// Creating a new project
app.post('/project/new', (req, res) => {
  let newProject = new Project()
  newProject.name = req.body.name
  newProject.users = [req.body.user]

  newProject.save((err, project) => {
    if (err) return res.status(500).send('Erreur')
    for (let user of newProject.users) {
      User.findOne({
        'email': user
      }, (err, user) => {
        if (err) return res.status(500)
        if (user) {
          user.projects.push(project.id)
          user.save((err) => {
            if (err) throw err
          })
        }
      })
    }
    return res.send('OK ' + project._id);
  })
})

// Getting a project
app.get('/project/:id', (req, res) => {
  Project.findOne({
    '_id': req.params.id
  }, (err, project) => {
    if (err) return res.status(500).send('Erreur!')
    if (project == null) res.status(404).send('Erreur: Projet inexistant')
    return res.json(project)
  })
})

// Getting users of project (members)
app.get('/project/:id/users', (req, res) => {
  Project.findOne({
    '_id': req.params.id
  }, (err, project) => {
    if (err) return res.status(500).send('Erreur!')
    if (project == null) return res.status(404).send('Erreur: Projet inexistant')
    return res.json(project.users)
  })
})

// Adding a user to a project
app.post('/project/:id/users/new', (req, res) => {
  let email = req.body.email

  User.update({
    email: email
  }, {
    $addToSet: {
      projects: req.params.id
    }
  }, (err, result) => {
    if (err) return res.status(500).send('Erreur!')
    if (result.nModified > 0) {
      Project.update({
        '_id': req.params.id
      }, {
        $addToSet: {
          users: email
        }
      }, (err) => {
        if (err) return res.status(500).send('Erreur!')
        return res.send('OK')
      })
    } else return res.send('Erreur: Utilisateur inexistant')
  })

})

// Adding and retreiving files
app.get('/project/:id/files', (req, res) => {
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      return res.json(project.files)
    })
  })
  .post('/project/:id/files/new', upload.single('fichier'), (req, res) => {
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      project.files.push({
        name: req.file.filename,
      })
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })

// Getting the conversation and adding a new one
app
  .get('/project/:id/conversation', (req, res) => {
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      return res.json(project.conversation)
    })
  })
  .post('/project/:id/conversation/new', (req, res) => {
    let email = req.body.email
    let content = req.body.content

    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      project.conversation.push({
        email: email,
        content: content
      })
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })

// Getting, adding and modifying tasks
app
  .get('/project/:id/tasks', (req, res) => {
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      return res.json(project.tasks)
    })
  })
  .post('/project/:id/tasks/new', (req, res) => {
    let description = req.body.description
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      project.tasks.push({
        description: description
      })
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })
  .post('/project/:id/tasks/:taskId', (req, res) => {
    let done = req.body.done
    let progress = req.body.progress
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')

      let task = project.tasks.id(req.params.taskId)
      task.done = done
      task.progress = progress
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })

// Getting, adding and modifying events
app
  .get('/project/:id/events', (req, res) => {
    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      return res.json(project.calendar)
    })
  })
  .post('/project/:id/events/new', (req, res) => {
    let title = req.body.title
    let description = req.body.description
    let date = req.body.date // change the format

    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')
      project.calendar.push({
        title, description, date
      })
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })
  .post('/project/:id/events/:eventId', (req, res) => {
    let title = req.body.title
    let description = req.body.description
    let date = req.body.date

    Project.findOne({
      '_id': req.params.id
    }, (err, project) => {
      if (err) return res.status(500).send('Erreur!')
      if (project == null) return res.status(404).send('Erreur: Projet inexistant')

      let event = project.calendar.id(req.params.eventId)
      event.title = title
      event.description = description
      event.date = date
      project.save((err) => {
        if (err) return res.status(500).send('Erreur')
        return res.send('OK')
      })
    })
  })


io.on('connection', (socket) => {
  let projectId = socket.request._query['projectId'];
  console.log('Connected !, joining project: ' + socket.request._query['projectId']);

  socket.join(projectId)

  socket.on('message', (msg) => {
    console.log('message: ' + msg)

    let msgObj = JSON.parse(msg)

    Project.findOne({ '_id': projectId }, (err, project) => {
      if (err) console.log(err)
      if (project == null) console.log('Erreur: Projet inexistant')
      project.conversation.push({
        email: msgObj.user,
        content: msgObj.msg
      })
      project.save((err) => {
        if (err) console.log('Erreur')
        socket.broadcast.emit('message-broadcast', msg)
      })
    })

  })

  socket.on("progress-change", (msg) => {
    console.log("progress change socket event")
    socket.broadcast.emit('progress-change-broadcast', msg)
  })

  socket.on("tasks-change", (msg) => {
    console.log("tasks change socket event")
    socket.broadcast.emit('tasks-change-broadcast', msg)
  })

  socket.on("files-change", (msg) => {
    console.log("files change socket event")
    socket.broadcast.emit('files-change-broadcast', msg)
  })

  socket.on("calendar-change", (msg) => {
    console.log("calendar change socket event")
    socket.broadcast.emit('calendar-change-broadcast', msg)
  })
})


http.listen(PORT, () => {
  console.log('Magic at port ' + PORT)
})