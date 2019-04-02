package com.bckj.nacosdemo.stat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    @PostMapping("statAddUser")
    public Object statAddUser(String name) {
        return "success add user: " + name;
    }

    @GetMapping("stat")
    public Object stat(String stat) {
        return "stat " + stat;
    }
}
