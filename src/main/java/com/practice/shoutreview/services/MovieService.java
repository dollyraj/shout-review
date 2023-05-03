package com.practice.shoutreview.services;

import com.practice.shoutreview.dto.MovieDto;

import java.util.List;


public interface MovieService {
    public MovieDto addMovie(MovieDto movieDto);
    MovieDto findMovieByTitle(String title);
    MovieDto findMovieById(Integer movieId);

    List<MovieDto> findMoviesByGenre(String genre);
}
