package com.bckj.nacosdemo.accountservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nacosdemo-stat-service", fallback = StatServiceClientFallback.class)
public interface StatServiceClient {

    @PostMapping(value = "/stat/statAddUser")
    String statAddUser(@RequestParam("name") String name);
}
