package com.servei.notifications_service;


import com.servei.notifications_service.models.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NotificationController {

    @RequestMapping(value = "/sendmail")
    public void sendmail(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("receiver", "ivancaballero9717@gmail.com");
        map.add("subject", null);
        map.add("notification", "test");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

       SentMail response = restTemplate.postForObject( Constants.URL, request , SentMail.class );

    }
}
