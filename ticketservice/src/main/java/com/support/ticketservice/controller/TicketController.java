package com.support.ticketservice.controller;

import com.support.ticketservice.entity.Ticket;
import com.support.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Forbidden");
            errorResponse.put("message", "Acceso denegado: Token ausente");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        Ticket createdTicket = ticketService.saveTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }
}