package com.servei.notifications_service;


import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.models.SentMail;
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

    private RestTemplate restTemplate;

    @Autowired
    public NotificationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/sendmail")
    public void sendmail(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("receiver", "ivancaballero9717@gmail.com");
        map.add("subject", "Hola que ase");
        map.add("notification", "test");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

       SentMail response = restTemplate.postForObject( Constants.URL_MAILGUN, request , SentMail.class);

    }
}
