package com.example.tugether_be;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class HealthController {

    @GetMapping("/")
    public String health(){
        return "8080 port server on";
    }
}
