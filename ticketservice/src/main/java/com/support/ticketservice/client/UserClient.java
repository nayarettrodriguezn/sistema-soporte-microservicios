package com.support.ticketservice.client;

import com.support.ticketservice.config.FeignClientConfig; 
import com.support.ticketservice.dto.UserRemoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "userservice", 
    url = "http://localhost:8081", 
    configuration = FeignClientConfig.class
)
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserRemoteDTO getUserById(@PathVariable("id") Long id);
}