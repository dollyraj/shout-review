package com.practice.shoutreview.services;

import com.practice.shoutreview.dto.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
     void addReview(ReviewDto reviewDto,Integer movieId);

     ReviewDto getReviewById(Integer reviewId);

}
