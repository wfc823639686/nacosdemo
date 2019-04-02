package com.bckj.nacosdemo.accountservice.client;

import org.springframework.stereotype.Component;

@Component
public class StatServiceClientFallback implements StatServiceClient {

    @Override
    public String statAddUser(String name) {
        return "error for statAddUser";
    }
}
