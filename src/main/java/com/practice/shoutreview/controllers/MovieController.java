package com.practice.shoutreview.controllers;

import com.practice.shoutreview.dto.MovieDto;
import com.practice.shoutreview.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<MovieDto> postMovie(@RequestBody @Validated MovieDto movieDto){
        return new ResponseEntity<>(movieService.addMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping("/title")
    public MovieDto findMovieByTitle(@RequestParam String title){
        return movieService.findMovieByTitle(title);
    }

    @GetMapping("/movieId")
    public MovieDto findMovieById(@RequestParam Integer movieId){
        return movieService.findMovieById(movieId);
    }

    @GetMapping("/genre")
    public List<MovieDto> findMovieByGenre(@RequestParam String genre){
        return movieService.findMoviesByGenre(genre);
    }

}