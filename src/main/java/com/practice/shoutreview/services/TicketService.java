package com.practice.shoutreview.services;

import com.practice.shoutreview.dto.BookingDto;
import com.practice.shoutreview.dto.TicketDto;

public interface TicketService {
     TicketDto bookTicket(BookingDto bookingDto);
     TicketDto getTicket(Integer id);
}
