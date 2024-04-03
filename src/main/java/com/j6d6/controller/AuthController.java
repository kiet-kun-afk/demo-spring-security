package com.j6d6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("message", "<h2 style=\"color: red;\">Access Denied</h2>");
        return "index";
    }
}