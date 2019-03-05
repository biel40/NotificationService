package com.servei.notifications_service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servei.notifications_service.mocks.ResponseVerificationToken;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ProtectedResourceController {

    @Value("${token.verificator.url}")
    String tokenVerificationUrl;

    @Autowired
    private RestTemplate restTemplate;
    public ProtectedResourceController() {

    }

    @RequestMapping("/getProtectedResource")
    public void getProtectedResource(HttpServletResponse response,
                                     @RequestHeader("Authorization") String token) throws IOException {

        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authentication", token);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);

        // We use the GSON library to deserialize the JSON we obtain from the Verifier.
        Gson gson = new GsonBuilder().create();
        String json = restTemplate.postForObject(tokenVerificationUrl, httpEntity, String.class);

        ResponseVerificationToken responseVerificationToken = gson.fromJson(json, ResponseVerificationToken.class);

        if (responseVerificationToken.isSuccess() ) {

            File xml = new File("./src/main/resources/exportacioDadesCentre.xml");
            response.addHeader("Content-disposition", "attachment;filename=exportacioDadesCentre.xml");
            response.setContentType("application/xml");

            try {
                InputStream inputStream = new FileInputStream(xml);

                // We copy the Stream to the response's output stream.
                IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/ErrorPage");
            }

        } else {
            response.sendRedirect("/ErrorPage");
        }
    }

    @RequestMapping("/ErrorPage")
    public ModelAndView getErrorPage() {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }


}
