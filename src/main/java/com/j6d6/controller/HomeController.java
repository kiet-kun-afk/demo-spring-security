package com.j6d6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping({ "index", "" })
    public String index(Model model) {
        model.addAttribute("message", "This is home page");
        return "index";
    }

    @RequestMapping("about")
    public String about(Model model) {
        model.addAttribute("message", "This is about page");
        return "index";
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("admin")
    public String admin(Model model) {
        // if (!request.isUserInRole("ADMIN")) {
        // return "redirect:/accessDenied";
        // }
        model.addAttribute("message", "This is ADMIN page");
        return "index";
    }

    // @PreAuthorize("hasRole('USER')")
    @RequestMapping("user")
    public String user(Model model) {
        // if (!request.isUserInRole("USER")) {
        // return "redirect:/accessDenied";
        // }
        model.addAttribute("message", "This is USER page");
        return "index";
    }

    // @PreAuthorize("isAuthenticated()")
    @RequestMapping("auth")
    public String authenticated(Model model) {
        // if (request.getRemoteUser() == null) {
        // return "redirect:/accessDenied";
        // }
        model.addAttribute("message", "This is AUTHENTICATE page");
        return "index";
    }
}