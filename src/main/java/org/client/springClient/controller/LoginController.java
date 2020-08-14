package org.client.springClient.controller;

import org.client.springClient.dto.AuthenticationRequestDto;
import org.client.springClient.dto.JwtTokenDto;
import org.client.springClient.model.Role;
import org.client.springClient.model.User;
import org.client.springClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
/*import org.springframework.security.core.Authentication;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class LoginController {

    @Value("${central.server.url}")
    private String mainURL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenDto jwtTokenDto;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password) {
        if (jwtTokenDto.getTokenValue() == null && jwtTokenDto.getUser() == null) {
            AuthenticationRequestDto userNameAndPassword = new AuthenticationRequestDto(username, password);
            AuthenticationRequestDto token = restTemplate.postForObject(mainURL + "/login",
                    new HttpEntity<>(userNameAndPassword),
                    AuthenticationRequestDto.class);
            if (token != null) {
                jwtTokenDto.setTokenValue(token.getPassword());
                jwtTokenDto.setUser(userService.findByName(token.getUsername()));
            } else {
                return null;
            }
            return "redirect:/newLogin/user";
        }
        return null;
    }

    @GetMapping("/newLogin/user")
    public String RedirectNewUser() {
        if (jwtTokenDto.getUser().isAdmin()) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @PostMapping("/logout")
    public String getLogout(){
        jwtTokenDto.setUser(null);
        jwtTokenDto.setTokenValue(null);
        return "loginPage";
    }
}
