package com.practice.shoutreview.controllers;

import com.practice.shoutreview.dto.ReviewDto;
import com.practice.shoutreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/add/{movieId}")
    public void addReview(@RequestBody ReviewDto reviewDto,@PathVariable("movieId") Integer movieId){
        reviewService.addReview(reviewDto,movieId);
    }

    @GetMapping("/find")
    public ReviewDto getReview(@RequestParam Integer reviewId){
        return reviewService.getReviewById(reviewId);
    }
}
