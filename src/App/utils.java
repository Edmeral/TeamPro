/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.File;
import java.io.IOException;
import models.Fichier;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author aissam
 */
public class utils {

    public static String post(String url, JSONObject jsonObj) throws IOException {
        String serverURL = config.serverURL;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String URL = serverURL + url;

        RequestBody body = RequestBody.create(JSON, jsonObj.toString());
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public static String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String URL = config.serverURL + url;

        Request request = new Request.Builder()
                .url(URL)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void upload(String path, String projectId) throws IOException {

        String filePath = path;
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient client = new OkHttpClient();

        Fichier fichier = new Fichier(filePath);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fichier", fichier.getNom(),
                        RequestBody.create(null, new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .url(config.serverURL + "/project/" + projectId + "/files/new")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            System.out.println("Unexpected code " + response);
        }

        System.out.println(response.body().string());
    }
    
    public static void download(String url) {
        
    }
    
    public static String SlashToTiret(String date) { // dd/mm/yyyy ==> yyyy-mm-dd
         String[] dateSplit = date.split("/");
         String newDate = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
         return newDate;
        
    }
    
    public static String TiretToSlash(String date) {  // yyyy-mm-dd => dd/mm/yyyy
        String[] dateSplit = date.split("-");
         String newDate = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];
         return newDate;
    }
    
    
    
}
