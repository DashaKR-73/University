package org.example.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("auth")
public class AuthRestController {
    private static final String AUTHORIZATION_URL =
            "http://localhost:8004/authorisation";
    @GetMapping("authorisation")
    public String auth(
            @RequestParam(value = "login", defaultValue = "")
            String login,
            @RequestParam(value = "password", defaultValue = "")
            String password
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String local_url = AUTHORIZATION_URL
                + "?login=" + login
                + "&password=" + password;
        ResponseEntity<String> result =
                restTemplate.getForEntity(local_url, String.class);
        return result.getBody();
    }
}
