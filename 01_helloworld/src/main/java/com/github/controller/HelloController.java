package com.github.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HAN
 * @version 1.0
 * @create 04-22-17:19
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Hello() {
        return "hello";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
