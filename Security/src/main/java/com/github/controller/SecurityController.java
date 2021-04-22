package com.github.controller;

import com.github.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 04-22-17:19
 */
@RestController
public class SecurityController {

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

    @GetMapping("secured")
    @Secured({"ROLE_sale", "ROLE_normal"})
    public String secured() {
        return "secured test";
    }

    @GetMapping("preAuthorize")
    @PreAuthorize("hasAuthority('admins')")
    public String preAuthorize() {
        return "preauthorize test";
    }

    @GetMapping("postFilter")
    @PostFilter("filterObject.name == 'andochiwa'")
    public List<User> postFilter() {
        List<User> list = new ArrayList<>();
        list.add(new User(null, "andochiwa", "y"));
        list.add(new User(null, "shizuku", "n"));
        return list;
    }
}
