package com.servei.notifications_service;


import com.servei.notifications_service.models.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class NotificationController {

    @RequestMapping(value = "/sendmail")
    public void sendmail() throws IOException, JSONException {
        URL url = new URL(null, "http://172.16.7.228:3000/sendMail");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", Constants.USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "receiver=ivancaballero9717@gmail.com&subject=prueba&notification=test";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //Obtenemos el codigo de la respuesta para saber si ha funcionado y controlar errores
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        //Comprovamos que hacemos la petición correctamente
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        //tracatament de la resposta
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print in String
        System.out.println(response.toString());

        //tractament del JSON per obtenir les dades
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println(myResponse.getString("id"));



    }
}
