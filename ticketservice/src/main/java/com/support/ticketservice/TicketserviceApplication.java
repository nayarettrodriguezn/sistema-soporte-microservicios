package com.support.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TicketserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketserviceApplication.class, args);
    }
}