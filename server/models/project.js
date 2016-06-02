'use strict'
const mongoose = require('mongoose')
const Schema = mongoose.Schema

let conversationSchema = Schema({
  email: String,
  content: String,
  timestamp: { type: Date, default: Date.now }
})

let taskSchema = Schema({
  description: String,
  done: { type: Boolean, default: false },
  timestamp: { type: Date, default: Date.now },
  progress: { type: Number, default: 0 }
})

let eventSchema = Schema({
  title: String,
  description: String,
  date: Date
})

let fileSchema = Schema({
  name: String,
})

let projectSchema = Schema({
  name: String,
  users: [String],
  tasks: [taskSchema],
  conversation: [conversationSchema],
  calendar: [eventSchema],
  files: []
})

module.exports = mongoose.model('Project', projectSchema);
