package com.servei.notifications_service.services.socket_notificator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SocketController {
    /*
    Aquesta classe en un futur es podra llevar ja que nomes serveix per donar un html y fer proves al html.
    En un futur el html vindra d'una altra part, y nomes conectara el socket
     */


    @Autowired
    Registers registers;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/index")
    public String getIndex(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}

