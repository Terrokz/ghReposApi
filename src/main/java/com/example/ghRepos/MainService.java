package com.example.ghRepos;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class MainService {

    public GHRepository getRepository(String owner, String repositoryName, String language){
        return parse(callGH(owner, repositoryName), language);
    }

    private static String callGH(String owner, String repositoryName){
        HttpURLConnection connection = null;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://api.github.com/repos/"+owner+"/"+repositoryName);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }

        return responseContent.toString();
    }

//parse response body from GH to GHRepository object
    private static GHRepository parse(String responseBody, String language){
        JSONObject responseJson = new JSONObject(responseBody);

        String fullName = responseJson.getString("full_name");
        String description = responseJson.get("description") instanceof String ?  responseJson.getString("description") : ""  ;
        String cloneUrl = responseJson.getString("clone_url");
        int stars = responseJson.getInt("stargazers_count");
        String createdAtString = responseJson.getString("created_at");
        Date createdAt;
        String formattedCreatedAt;
        try {
            createdAt = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")).parse(createdAtString);
            String oneLanguage = language.split(",")[0];
            DateFormat dateFormatter =DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale(oneLanguage));
            formattedCreatedAt = dateFormatter.format(createdAt);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new GHRepository(fullName, description, cloneUrl, stars, formattedCreatedAt);
    }




}
