package com.seonko.OurLittleDiary.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class OauthService {

    @Value("${GOOGLE_CLIENT_ID}")
    private String clientID;
    @Value("${GOOGLE_CLIENT_SECRET}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    public String getGoogleAccessToken(String authorize_code) throws Exception {
        String access_token = "";
        String reqURL = "https://oauth2.googleapis.com/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + clientID);
            sb.append("&client_secret=" + clientSecret);
            sb.append("&redirect_uri=" + redirectUri);
            sb.append("&code=" + authorize_code);

            bw.write(sb.toString());
            bw.flush();
            bw.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb2 = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb2.append(line);
                }
                JsonObject jsonObject = JsonParser.parseString(sb2.toString()).getAsJsonObject();
                access_token = jsonObject.get("access_token").toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return access_token;
    }

//    public HashMap<String, String> getGoogleUserInfo(String access_Token) throws Exception {
//        HashMap<String, String> userInfo = new HashMap<>();
//        String reqURL = "https://";
//
//        try {
//
//        }
//    }
}
