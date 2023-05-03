package com.practice.shoutreview.controllers;

import com.practice.shoutreview.dto.BookingDto;
import com.practice.shoutreview.dto.TicketDto;
import com.practice.shoutreview.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketDto> bookTicket(@RequestBody BookingDto bookingDto) {

        //log.info("Received Request to book ticket: " + bookingResource);

        return ResponseEntity.ok(ticketService.bookTicket(bookingDto));
    }

    //@Min(value = 1, message = "Ticket Id Cannot be -ve")
    @GetMapping("{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable(name = "id")  Integer id) {

//        log.info("Received Request to get ticket: " + id);

        return ResponseEntity.ok(ticketService.getTicket(id));
    }
}
