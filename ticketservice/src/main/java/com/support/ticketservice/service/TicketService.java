package com.support.ticketservice.service;

import com.support.ticketservice.client.UserClient;
import com.support.ticketservice.dto.UserRemoteDTO;
import com.support.ticketservice.entity.Ticket;
import com.support.ticketservice.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserClient userClient;

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    public Ticket saveTicket(Ticket ticket) {
        log.info("Initiating remote validation for User ID: {} to assign a new ticket.", ticket.getUserId());
        try {
            UserRemoteDTO remoteUser = userClient.getUserById(ticket.getUserId());

            if (remoteUser == null) {
                log.warn("Remote microservice returned an empty user response.");
                throw new RuntimeException("El usuario remoto no es válido.");
            }

            log.info("Remote user verification successful for name: {}. Proceeding with persistence.", remoteUser.getName());
            
            if (ticket.getStatus() == null) {
                ticket.setStatus("OPEN");
            }

            Ticket savedTicket = ticketRepository.save(ticket);
            log.info("Ticket successfully registered with ID: {}", savedTicket.getId());
            return savedTicket;

        } catch (Exception e) {
            log.error("Communication failure or invalid entity mapping on remote request: {}", e.getMessage());
            throw new RuntimeException("No se pudo crear el ticket: El usuario ingresado no existe en el sistema.");
        }
    }

    public List<Ticket> getAllTickets() {
        log.info("Fetching all service tickets from local database.");
        return ticketRepository.findAll();
    }
}