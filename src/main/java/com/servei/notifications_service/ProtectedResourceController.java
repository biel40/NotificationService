package com.servei.notifications_service;

import com.servei.notifications_service.mocks.MockVerification;
import com.servei.notifications_service.mocks.TokenVerificator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@RestController
public class ProtectedResourceController {

    public ProtectedResourceController() {

    }

    @RequestMapping("/getProtectedResource")
    public void getProtectedResource(HttpServletResponse response, HttpSession session) throws IOException {

        //  Recibimos el token, se lo enviamos a su "Validator".
        //  y obtenemos su respueta. Al obtener su respuesta, hacemos la redirección.

        // The Token is not static. We have to get it from the Red Service.
        String token = "AACbpkMFarNdMwz1qVPV0mWcnfjSt0zMcNcUogSMgr2lcZU2G7qjf7B-f1lmTkhRpfgXFBwxzd9ad3vRD1Oymgk#";

        TokenVerificator tokenVerificator = new MockVerification();

        if (tokenVerificator.verifyToken(token) && session != null )  {

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
        //TODO:
        ModelAndView modelAndView = new ModelAndView("");
        return modelAndView;
    }
}
