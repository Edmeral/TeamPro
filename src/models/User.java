package models;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import App.config;
import App.utils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class User {

    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // returns "OK" if no error occured while saving the new user,
    // otherwise returns an error message
    public String save() throws IOException {
        return utils.post("/user", new JSONObject().put("email", email).put("password", password));
    }

    public String connect() throws IOException {
        return utils.post("/user/" + email, new JSONObject().put("password", password));
    }

    public HashMap<String, String> getProjects() throws IOException {
        JSONArray projects = new JSONArray(utils.get("/user/" + email +"/projects"));
        
        HashMap<String, String> projectsNames = new HashMap<>();
        
        for (int i = 0; i< projects.length(); i++) {
            JSONObject project = projects.getJSONObject(i);
            projectsNames.put((String) project.get("name"), (String) project.get("_id"));
        }
        return projectsNames;
    }

}
