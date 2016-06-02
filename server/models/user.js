'use strict'

const mongoose = require('mongoose')

let userSchema = mongoose.Schema({
  email: String,
  password: String,
  projects: [mongoose.Schema.Types.ObjectId]
})

module.exports = mongoose.model('User', userSchema);
