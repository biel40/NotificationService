package com.servei.notifications_service;

import com.servei.notifications_service.mocks.ResponseVerificationToken;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ProtectedResourceController {

    private String googleTokenUrl;
    private String verifyTokenUrl;

    public ProtectedResourceController() {

    }

    @RequestMapping("/getProtectedResource")
    public void getProtectedResource(HttpServletResponse response) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String token = restTemplate.getForObject(googleTokenUrl, String.class);

        //TODO: Falta mirar las Headers del Token? Además de mirar si me hace bien la deserialización del JSON.
        ResponseVerificationToken responseVerificationToken = restTemplate.postForObject(verifyTokenUrl, token, ResponseVerificationToken.class);

        if (responseVerificationToken.isSuccess()) {

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

    @Autowired
    public void setGoogleTokenUrl(String googleTokenUrl) {
        this.googleTokenUrl = googleTokenUrl;
    }

    @Autowired
    public void setVerifyTokenUrl(String verifyTokenUrl) {
        this.verifyTokenUrl = verifyTokenUrl;
    }

}
