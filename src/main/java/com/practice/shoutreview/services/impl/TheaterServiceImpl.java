package com.practice.shoutreview.services.impl;

import com.practice.shoutreview.dto.TheaterDto;
import com.practice.shoutreview.entities.Theater;
import com.practice.shoutreview.entities.TheaterSeats;
import com.practice.shoutreview.enums.SeatType;
import com.practice.shoutreview.exceptions.ResourceNotFoundException;
import com.practice.shoutreview.repositories.TheaterRepository;
import com.practice.shoutreview.repositories.TheaterSeatsRepository;
import com.practice.shoutreview.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatsRepository theaterSeatsRepository;


    public TheaterDto addTheater(TheaterDto theaterDto) {

        Theater theater = Theater.DtoToTheater(theaterDto);

        theater.getSeats().addAll(getTheaterSeats());

        for (TheaterSeats seatsEntity : theater.getSeats()) {
            seatsEntity.setTheater(theater);
        }

        theater = theaterRepository.save(theater);

        //System.out.println(theater);
        //log.info("Added New User [id: " + theater.getId() + ", Name: " + theater.getName() + "]");

        return Theater.TheaterToDto(theater);
    }

    private List<TheaterSeats> getTheaterSeats() {

        List<TheaterSeats> seats = new ArrayList<>();

        seats.add(getTheaterSeat("1A", SeatType.REGULAR));
        seats.add(getTheaterSeat("1B", SeatType.REGULAR));
        seats.add(getTheaterSeat("1C", SeatType.REGULAR));
        seats.add(getTheaterSeat("1D", SeatType.REGULAR));
        seats.add(getTheaterSeat("1E", SeatType.REGULAR));

        seats.add(getTheaterSeat("2A", SeatType.RECLINER));
        seats.add(getTheaterSeat("2B", SeatType.RECLINER));
        seats.add(getTheaterSeat("2C", SeatType.RECLINER));
        seats.add(getTheaterSeat("2D", SeatType.RECLINER));
        seats.add(getTheaterSeat("2E", SeatType.RECLINER));

        seats = theaterSeatsRepository.saveAll(seats);

        return seats;
    }

    private TheaterSeats getTheaterSeat(String seatNumber, SeatType seatType) {
        TheaterSeats theaterSeats=new TheaterSeats();
        theaterSeats.setSeatNumber(seatNumber);
        theaterSeats.setSeatType(seatType);
        return theaterSeats;
    }


    public TheaterDto getTheater(Integer id) {
      //  log.info("Searching Theater by id: " + id);



        Optional<Theater> theater= theaterRepository.findById(id);



        if (theater.isEmpty()) {
           // log.error("Theater not found for id: " + id);
            throw new ResourceNotFoundException("Theater","Id",id);
        }

        return Theater.TheaterToDto(theater.get());
    }



}
