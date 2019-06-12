package com.spring.microservices.client;

import com.spring.microservices.client.model.Profile;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service")
public interface ProfileClient {
    @GetMapping("/profile/{userId}")
    public Profile profile(@PathVariable("userId") Long id);
}