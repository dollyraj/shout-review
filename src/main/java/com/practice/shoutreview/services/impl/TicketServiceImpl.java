package com.practice.shoutreview.services.impl;

import com.practice.shoutreview.dto.BookingDto;
import com.practice.shoutreview.dto.TicketDto;
import com.practice.shoutreview.entities.Show;
import com.practice.shoutreview.entities.ShowSeat;
import com.practice.shoutreview.entities.Ticket;
import com.practice.shoutreview.entities.User;
import com.practice.shoutreview.exceptions.ResourceNotFoundException;
import com.practice.shoutreview.repositories.ShowsRepository;
import com.practice.shoutreview.repositories.TicketRepository;
import com.practice.shoutreview.repositories.UserRepository;
import com.practice.shoutreview.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
   /* @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static String topic="TICKET_BOOKED";

    ObjectMapper mapper=new ObjectMapper();*/

    public TicketDto bookTicket(BookingDto bookingDto) {

        //First find if user is present with given userId
        Optional<User> optionalUser = userRepository.findById(bookingDto.getUserId());

        if (optionalUser.isEmpty()) {
            throw  new ResourceNotFoundException("User","Id",bookingDto.getUserId());

        }

        //Then find if show with given showId is present

        Optional<Show> optionalShow = showsRepository.findById(bookingDto.getShowId());

        if (optionalShow.isEmpty()) {
            throw  new ResourceNotFoundException("User","Id",bookingDto.getShowId());

        }

        //unique seat numbers requested for booking
        Set<String> requestedSeats = bookingDto.getSeatsNumbers();

        //System.out.println(requestedSeats);
        //Find if requested seat number and seat type is available
        List<ShowSeat> showSeatsEntities = optionalShow.get().getSeats();

        showSeatsEntities =
                showSeatsEntities
                        .stream()
                        .filter(seat -> seat.getSeatType().equals(bookingDto.getSeatType())
                                && !seat.isBooked()
                                && requestedSeats.contains(seat.getSeatNumber()))
                        .collect(Collectors.toList());


        //if not available throw exception
        if (showSeatsEntities.size() != requestedSeats.size()) {
            throw new ResourceNotFoundException("Seats Not Available for Booking");
        }

        //if available proceed for booking the ticket
       /* Ticket ticket =new Ticket();
        ticket.setUser(optionalUser.get());
        ticket.setShow(optionalShow.get());
        ticket.setSeats(showSeatsEntities);*/

        Ticket ticket =
                Ticket.builder()
                        .user(optionalUser.get())
                        .show(optionalShow.get())
                        .seats(showSeatsEntities)
                        .build();


        double amount = 0.0;
        String allotedSeats = "";

        for (ShowSeat seatsEntity : showSeatsEntities) {
            seatsEntity.setBooked(true);
            seatsEntity.setBookedAt(new Date());
            seatsEntity.setTicket(ticket);

            amount += seatsEntity.getRate();

            allotedSeats += seatsEntity.getSeatNumber() + " ";
        }

        ticket.setAmount(amount);
        ticket.setAllottedSeats(allotedSeats);

        if (CollectionUtils.isEmpty(optionalUser.get().getTicketEntities())) {
            optionalUser.get().setTicketEntities(new ArrayList<>());
        }

        optionalUser.get().getTicketEntities().add(ticket);

        if (CollectionUtils.isEmpty(optionalShow.get().getTickets())) {
            optionalShow.get().setTickets(new ArrayList<>());
        }

        optionalShow.get().getTickets().add(ticket);

        ticket = ticketRepository.save(ticket);

//        try {
//            TicketMessage message = new TicketMessage(ticket.getUser().getName(),ticket.getUser().getMobile(),ticket.getUser().getEmail(), ticket.getShow(), ticket.getSeats());
//            log.info("sending kafka message on booking {}", mapper.writeValueAsString(message));
//            kafkaTemplate.send(topic, mapper.writeValueAsString(message));
//        }catch (Exception e){
//            log.error("Exception while sending notification service");
//        }

        return Ticket.TicketToDto(ticket);
    }


    public TicketDto getTicket(Integer id) {
        Optional<Ticket> ticketEntity = ticketRepository.findById(id);

        if (ticketEntity.isEmpty()) {
           // log.error("Ticket not found for id: " + id);
            throw new ResourceNotFoundException("Ticket","Id",id);

        }

        return Ticket.TicketToDto(ticketEntity.get());
    }

}
