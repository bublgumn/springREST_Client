package org.client.springClient.controller;

import org.client.springClient.model.User;
import org.client.springClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String mainPageAdmin(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        List<User> listAllUsers = userService.findAll();
        model.addAttribute("mainUser", user);
        model.addAttribute("isRole", user.isAdmin());
        model.addAttribute("users", listAllUsers);
        return "mainPage";
    }

    @GetMapping("/user")
    public String MainPageUser(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("mainUser", user);
        model.addAttribute("isRole", user.isAdmin());
        return "mainPage";
    }

}
