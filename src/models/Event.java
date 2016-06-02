/*
 * 
 * A calendar is a collection of events
 * 
 */
package models;

import org.json.JSONObject;
/**
 *
 * @author aissam
 */
public class Event {
    
     String title;
     String date;
     String id;
     String description; 
    
    
    public Event(JSONObject eventJSON) {
        this.title = eventJSON.getString("title");
        this.date = eventJSON.getString("date").substring(0, 10);
        this.id = eventJSON.getString("_id");
        this.description = eventJSON.getString("description");
        
    }
    
    public String toString() {
        return title + " " + date +  " " + id + " " + description;
     }
//    public Event(String description, int timestamp) {
//        this.description = description;
//        this.timestamp = timestamp;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getTimestamp() {
//        return timestamp;
//    }
//    
    
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
    
    public int getJour() {
        String[] dt = date.split("-");
        return Integer.parseInt(dt[2]);
    }

    public int getMois() {
        String[] dt = date.split("-");
        return Integer.parseInt(dt[1]);
    }

    public int getAnnee() {
        String[] dt = date.split("-");
        return Integer.parseInt(dt[0]);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
