package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final String HARDCODED_USERNAME = "Carter";
    private static final String HARDCODED_PASSWORD = "Password907";

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {


        if (username.equals(HARDCODED_USERNAME) && password.equals(HARDCODED_PASSWORD)) {
//            model.addAttribute("username", username);
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/mainscreen";
        } else  {
//          model.addAttribute("loginError", true);
            return "redirect:/login?error";
        }
    }
    // Mapping for mainscreen page
    @RequestMapping("/mainscreen")
    public String mainscreen(Model model, @ModelAttribute("username") String username) {

        model.addAttribute("username", username);
        return "mainscreen"; // returns mainscreen.html
    }

    @RequestMapping("/login")
    public String loginError(@ModelAttribute("loginError") boolean loginError, Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
