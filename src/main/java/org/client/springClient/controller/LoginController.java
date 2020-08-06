package org.client.springClient.controller;

import org.client.springClient.model.Role;
import org.client.springClient.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/newLogin/user")
    public String RedirectNewUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        for (Role role : user.getRole()
        ) {
            if (role.getName().equals("ROLE_admin")) {
                return "redirect:/admin";
            }
        }
        return "redirect:/user";
    }
}
