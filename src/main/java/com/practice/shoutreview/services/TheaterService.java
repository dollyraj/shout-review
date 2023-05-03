package com.practice.shoutreview.services;

import com.practice.shoutreview.dto.TheaterDto;

public interface TheaterService {
    public TheaterDto addTheater(TheaterDto theaterDto);
    public TheaterDto getTheater(Integer id);
}
