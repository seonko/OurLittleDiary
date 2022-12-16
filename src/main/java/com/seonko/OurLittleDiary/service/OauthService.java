package com.seonko.OurLittleDiary.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
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
        String id_token = "";
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
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println(sb2);
                JsonObject jsonObject = JsonParser.parseString(sb2.toString()).getAsJsonObject();
                access_token = jsonObject.get("access_token").toString();
                id_token = jsonObject.get("id_token").toString();
                Base64.Decoder decoder = Base64.getDecoder();
                String payload = new String(decoder.decode(id_token));
                System.out.println(payload);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return access_token;
    }

    public void getGoogleUserInfo(String accessToken) throws Exception {
        System.out.println("check");
        HashMap<String, String> userInfo = new HashMap<>();
        String reqURL = "https://www.googleapis.com/drive/v2/files";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(reqURL, HttpMethod.GET, request, String.class);
            System.out.println(response);
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//            conn.setDoOutput(true);
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            bw.flush();
//            bw.close();
//
//            int responseCode = conn.getResponseCode();
//            System.out.println(responseCode);
//            if (responseCode == 200) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                StringBuilder sb2 = new StringBuilder();
//                String line = "";
//                while ((line = br.readLine()) != null) {
//                    sb2.append(line);
//                }
//                JsonObject jsonObject = JsonParser.parseString(sb2.toString()).getAsJsonObject();
//                System.out.println(jsonObject.toString());
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
