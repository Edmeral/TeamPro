/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import App.config;
import App.utils;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author aissam
 */
public class Project {

    private String id;
    private String name;
    private ArrayList<User> users;
    public ArrayList<String> usersEmails;
    private ArrayList<Fichier> files;
    public ArrayList<Task> tasks;
    private ArrayList<Event> calendar;
    private ArrayList<Message> conversation;

    public Project(String name, User user) {
        this.name = name;
        users = new ArrayList<User>();
        users.add(user);
    }

    public String create() throws IOException {
        String serverURL = config.serverURL;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        JSONObject projectJson = new JSONObject();

        projectJson.put("name", name);
        projectJson.put("user", users.get(0).getEmail());

        String url = serverURL + "/project/new";

        RequestBody body = RequestBody.create(JSON, projectJson.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public Project(JSONObject projectJSON) {
        this.id = projectJSON.getString("_id");
        this.name = projectJSON.getString("name");

        // Adding users
        this.usersEmails = new ArrayList<>();
        JSONArray emails = projectJSON.getJSONArray("users");
        for (int i = 0; i < emails.length(); i++) {
            this.usersEmails.add(emails.getString(i));
        }

        // Adding tasks
        this.tasks = new ArrayList<>();
        JSONArray tasksJSON = projectJSON.getJSONArray("tasks");
        for (int i = 0; i < tasksJSON.length(); i++) {
            this.tasks.add(new Task(tasksJSON.getJSONObject(i)));
        }

        // Adding conversation
        this.conversation = new ArrayList<>();
        JSONArray conversationJSON = projectJSON.getJSONArray("conversation");
        for (int i = 0; i < conversationJSON.length(); i++) {
            this.conversation.add(new Message(conversationJSON.getJSONObject(i)));
        }

        // Adding events to the calendar 
        this.calendar = new ArrayList<>();
        JSONArray calendarJSON = projectJSON.getJSONArray("calendar");
        for (int i = 0; i < calendarJSON.length(); i++) {
            this.calendar.add(new Event(calendarJSON.getJSONObject(i)));
        }

        // Adding files
        this.files = new ArrayList<>();
        JSONArray filesJSON = projectJSON.getJSONArray("files");
        for (int i = 0; i < filesJSON.length(); i++) {
            this.files.add(new Fichier(filesJSON.getJSONObject(i).getString("name")));
        }
    }

    public void update() {
        this.updateTasks();
        this.updateFiles();
        this.updateEvents();
        this.updateUsers();
    }

    public void display() {
        System.out.println(name);
        System.out.println(id);
        System.out.println(usersEmails);
        System.out.println(tasks);
        System.out.println(conversation);
        System.out.println(calendar);
    }

    public Project(String name, ArrayList<User> users, ArrayList<Fichier> files, ArrayList<Task> tasks, ArrayList<Event> calendar, ArrayList<Message> conversation) {
        this.name = name;
        this.users = users;
        this.files = files;
        this.tasks = tasks;
        this.calendar = calendar;
        this.conversation = conversation;
    }

    public void updateTask(Task task, String desc, int progress) {
        boolean done = progress == 100 ? true : false;
        try {
            utils.post("/project/" + this.id + "/tasks/" + task.id, new JSONObject().put("done", done).put("progress", progress));
            this.updateTasks();
        } catch (IOException ex) {
            System.out.println("IOException while updating one task");
        }
    }

    public void updateUsers() {
        try {
            String res = utils.get("/project/" + this.id + "/users");
            this.usersEmails = new ArrayList<>();
            JSONArray emails = new JSONArray(res);
            for (int i = 0; i < emails.length(); i++) {
                this.usersEmails.add(emails.getString(i));
            }
        } catch (IOException ex) {
            System.out.println("IOExcepetion: Erreur Réseau");
        }
    }

    public String updateEvent(String title, String description, String date, String eventId) {
        JSONObject obj = new JSONObject().put("title", title)
                .put("description", description)
                .put("date", date);
        try {
            String res = utils.post("/project/" + this.id + "/events/" + eventId, obj);
            this.updateEvents();
            return res;
        } catch (IOException ex) {
            return "IOExcepetion: Erreur Réseau";
        }

    }

    private void updateTasks() {

        try {
            String res = utils.get("/project/" + this.id + "/tasks");
            this.tasks = new ArrayList<>();
            JSONArray tasksJSON = new JSONArray(res);
            for (int i = 0; i < tasksJSON.length(); i++) {
                this.tasks.add(new Task(tasksJSON.getJSONObject(i)));
            }
        } catch (IOException ex) {
            System.out.println("IOException while updating tasks");
        }
    }

    private void updateEvents() {
        try {
            String res = utils.get("/project/" + this.id + "/events");
            this.calendar = new ArrayList<>();
            JSONArray conversationJSON = new JSONArray(res);
            for (int i = 0; i < conversationJSON.length(); i++) {
                this.calendar.add(new Event(conversationJSON.getJSONObject(i)));
            }
        } catch (IOException ex) {
            System.out.println("IOException while updating tasks");
        }

    }
    
    public void updateConversation() {
        try {
            String res = utils.get("/project/" + this.id + "/conversation");
            this.conversation = new ArrayList<>();
            JSONArray conversationJSON = new JSONArray(res);
            for (int i = 0; i < conversationJSON.length(); i++) {
                this.conversation.add(new Message(conversationJSON.getJSONObject(i)));
            }
        } catch (IOException ex) {
            System.out.println("IOException while updating conversation");
        }
        
    }
    
    public void updateFiles() {
        // Adding files
        try {
            String res = utils.get("/project/" + this.id + "/files");
            this.files = new ArrayList<>();
            JSONArray filesJSON = new JSONArray(res);
            for (int i = 0; i < filesJSON.length(); i++) {
                this.files.add(new Fichier(filesJSON.getJSONObject(i).getString("name")));
            }
        } catch (IOException ex) {
            System.out.println("IOException while updating tasks");
        }
    }

    public int getProgress() {
        if (tasks.size() == 0) {
            return 0;
        } else {
            int sum = 0;
            for (Task task : tasks) {
                sum += task.progress;
            }
            return sum / tasks.size();
        }
    }

    public String addUser(String email) {
        try {
            return utils.post("/project/" + this.id + "/users/new", new JSONObject().put("email", email));
        } catch (IOException ex) {
            return "IOExcepetion: Erreur Réseau";
        }
    }

    public String addTask(String taskName) {
        try {
            String res = utils.post("/project/" + this.id + "/tasks/new", new JSONObject().put("description", taskName));
            this.updateTasks();
            return res;
        } catch (IOException ex) {
            return "IOExcepetion: Erreur Réseau";
        }

    }

    public String addEvent(String title, String description, String date) {
        JSONObject obj = new JSONObject().put("title", title)
                .put("description", description)
                .put("date", date);
        try {
            String res = utils.post("/project/" + this.id + "/events/new", obj);
            this.updateEvents();
            return res;
        } catch (IOException ex) {
            return "IOExcepetion: Erreur Réseau";
        }

    }

    public String addFile(String path) {
        try {
            utils.upload(path, this.id);
            this.updateFiles();
            return "OK";
        } catch (IOException ex) {
            return "IOException: Erreur réseau";
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Fichier> getFiles() {
        return files;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Event> getCalendar() {
        return calendar;
    }

    public ArrayList<Message> getConversation() {
        return conversation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setFiles(ArrayList<Fichier> files) {
        this.files = files;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setCalendar(ArrayList<Event> calendar) {
        this.calendar = calendar;
    }

    public void setConversation(ArrayList<Message> conversation) {
        this.conversation = conversation;
    }

}
