package models;

public class Fichier {
    private String name;
    private String url;
    private String uploaderEmail; // The email adresss of the one who uploaded it

    public Fichier(String path){
        name = path;
        url = path;
    }
    
    public String getExtension(){
        String ext = "";
        for(String s : name.split("[.]"))
            ext = s;
        return ext;
    }
    
    public String getNom(){
        String nom = "";
        for(String s : name.split("[\\\\]"))
            nom = s;
        return nom;
    }
    
    public Fichier(String name, String url, String uploaderEmail) {
        this.name = name;
        this.url = url;
        this.uploaderEmail = uploaderEmail;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUploaderEmail() {
        return uploaderEmail;
    }
    
    
    
}
