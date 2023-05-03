package com.practice.shoutreview.dto;

import com.practice.shoutreview.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class ShowSeatsDto {
    private long id;

    private String seatNumber;

    private int rate;

    private SeatType seatType;

    private boolean booked;

    private Date bookedAt;


}
