package com.practice.shoutreview.services.impl;

import com.practice.shoutreview.dto.MovieDto;
import com.practice.shoutreview.entities.Movie;
import com.practice.shoutreview.enums.Genre;
import com.practice.shoutreview.exceptions.ResourceNotFoundException;
import com.practice.shoutreview.repositories.MovieRepository;
import com.practice.shoutreview.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Override
    public MovieDto addMovie(MovieDto movieDto) {

        Movie movie=Movie.DTOtoMovie(movieDto);
        if (movieRepository.existsByTitle(movieDto.getTitle())) {
            return Movie.movieToDTO(movie);
        }
        Movie savedMovie=movieRepository.save(movie);

      //  log.info("Added New Movie"+ movie.toString());

        return Movie.movieToDTO(savedMovie);
    }
    @Override
    public MovieDto findMovieByTitle(String title) {
        Movie movie=movieRepository.findByTitle(title);
        if(Objects.isNull(movie)){
            throw new ResourceNotFoundException("Movie","Title",title);
        }
        return Movie.movieToDTO(movie);
    }
    public MovieDto findMovieById(Integer id) {
        //add id check if valid
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new ResourceNotFoundException("Movie","Id",id);
        }

        return Movie.movieToDTO(movie.get());
    }
    @Override
    public List<MovieDto> findMoviesByGenre(String genre) {

        if (Arrays.stream(Genre.values()).noneMatch(g -> g.toString().equals(genre)))
            return new ArrayList<>();
        List<Movie> movieList = movieRepository.findByGenre(Genre.valueOf(genre));
        if (!movieList.isEmpty()) {
            List<Movie> movieResponseList = movieList.stream().sorted(Comparator.comparing(Movie::getRating, Comparator.reverseOrder())).collect(Collectors.toList());
            List<MovieDto> movieDtoList=new ArrayList<>();
            for(Movie movie:movieResponseList){
                movieDtoList.add(Movie.movieToDTO(movie));
            }
            if (movieDtoList.size() > 5)
                return movieDtoList.subList(0, 4);
            return movieDtoList;
        }
        return new ArrayList<>();
    }

}
