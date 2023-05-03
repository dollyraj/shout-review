package com.practice.shoutreview.services.impl;

import com.practice.shoutreview.dto.ReviewDto;
import com.practice.shoutreview.entities.Movie;
import com.practice.shoutreview.entities.Review;
import com.practice.shoutreview.exceptions.ResourceNotFoundException;
import com.practice.shoutreview.repositories.MovieRepository;
import com.practice.shoutreview.repositories.ReviewRepository;
import com.practice.shoutreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public void addReview(ReviewDto reviewDto, Integer movieId) {
        Optional<Movie> OptionalMovie=movieRepository.findById(movieId);
        if(OptionalMovie.isEmpty()){
            throw new ResourceNotFoundException("Movie","Id",movieId);
        }
        //System.out.println(reviewDto);

        Movie movie=OptionalMovie.get();
        Review review=Review.DTOtoReview(reviewDto);
        review.setMovie(movie);
        reviewRepository.save(review);
        //need to optimized
        //exception handling.
        if(movie!=null) {

            Double average = reviewRepository.getReviewAverage(movie.getId());
            movie.setRating(average);
            movieRepository.save(movie);
        }
    }

    @Override
    public ReviewDto getReviewById(Integer reviewId) {
        Optional<Review> review=reviewRepository.findById(reviewId);

        if(review.isEmpty()){
            throw new ResourceNotFoundException("Review","Id",reviewId);
        }

        return Review.ReviewToDTO(review.get());
    }


}
