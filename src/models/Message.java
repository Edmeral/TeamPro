/*
 * 
 * This is the object that makes a conversation (conversation = lot of messages)
 * 
 */
package models;

import org.json.JSONObject;

/**
 *
 * @author aissam
 */
public class Message {
    private String email;
    private String id;
    private String timestamp;
    private String content;

    
    public Message(JSONObject messageJSON) {
        this.email = messageJSON.getString("email");
        this.id = messageJSON.getString("_id");
        this.timestamp = messageJSON.getString("timestamp");
        if (messageJSON.has("content"))
            this.content = messageJSON.getString("content");
        else  this.content = "";
    }
//    public Message(String senderEmail, String content, int timestamp) {
//        this.senderEmail = senderEmail;
//        this.content = content;
//        this.timestamp = timestamp;
//    }
//
//    public String getSenderEmail() {
//        return senderEmail;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public int getTimestamp() {
//        return timestamp;
//    }
//    

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
    
    
}
