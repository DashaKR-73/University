package org.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("classes")
public class RedirectRestController {

    private static final String DATA_URL =
            "http://localhost:8080/";
    @Autowired
    private HttpServletRequest request;
    @GetMapping("{path}")
    public List<?> getAll(
            @PathVariable("path") String path
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String local_url = DATA_URL + path;
        HttpEntity<Void> entity = new HttpEntity<>(collectAllHeader());
        ResponseEntity<List> response =
                restTemplate.exchange(
                        local_url,
                        HttpMethod.GET,
                        entity,
                        List.class);
        return response.getBody();
    }
    @GetMapping("{path}/{id}")
    public Object getById(
            @PathVariable("path") String path,
            @PathVariable("id") Long id
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String local_url = DATA_URL + path + "/" + id;
        HttpEntity<Void> entity = new HttpEntity<>(collectAllHeader());
        ResponseEntity<Object> response =
                restTemplate.exchange(
                        local_url,
                        HttpMethod.GET,
                        entity,
                        Object.class);
        return response.getBody();
    }
    @DeleteMapping("{path}/{id}")
    public void deleteById(
            @PathVariable("path") String path,
            @PathVariable("id") Long id
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String local_url = DATA_URL + path + "/" + id;
        HttpEntity<Void> entity = new HttpEntity<>(collectAllHeader());
        restTemplate.exchange(
                local_url,
                HttpMethod.DELETE,
                entity,
                Void.class);
    }
    @PostMapping("{path}")
    public Object saveOrUpdate(
            @PathVariable("path") String path,
            @RequestBody Object dto
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String local_url = DATA_URL + path;
        HttpEntity<Object> entity =
                new HttpEntity<>(dto, collectAllHeader());
        ResponseEntity<Object> response =
                restTemplate.exchange(
                        local_url,
                        HttpMethod.POST,
                        entity,
                        Object.class);
        return response.getBody();
    }
    private HttpHeaders collectAllHeader() {
        HttpHeaders headers = new HttpHeaders();
        Collections
                .list(request.getHeaderNames())
                .forEach(s ->
                        headers.addAll(
                                s,
                                Collections.list(request.getHeaders(s))
                        ));
        return headers;
    }
}