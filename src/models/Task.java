/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.JSONObject;
/**
 *
 * @author aissam
 */
public class Task {
    String description;
    String id;
    int progress;
    String timestamp;
    boolean done;    
    
    public Task(JSONObject taskJSON) {
       description = taskJSON.getString("description");
       id = taskJSON.getString("_id");
       progress = taskJSON.getInt("progress");
       timestamp = taskJSON.getString("timestamp");
       done = taskJSON.getBoolean("done");
    }
    
     public String toString() {
        return description + " " + id +  " " + progress + " " + timestamp + " " + done; 
     }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public int getProgress() {
        return progress;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isDone() {
        return done;
    }
   
     
}
