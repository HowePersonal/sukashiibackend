package com.example.sukashii.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/oauth2")
    @ResponseBody
    public String ouath2Login() {
        return "<a href=\"/oauth2/authorization/google\">Google</a>";
    }

    @GetMapping("/oauth2/callback")
    public String oauth2LoginCallback() {
        return "callback";
    }

}
