package com.myapp.api.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/api/hola")
    public String hello() {
        return "Hello, World!";
    }

}