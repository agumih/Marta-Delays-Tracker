package com.gumih.marta_delays_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";  // templates/login.html
    }

//    @GetMapping("/dashboard")
//    public String dashboard () {
//        return "dashboard";  // templates/dashboard.html
//    }

}
