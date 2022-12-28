package com.userService.UserService.feignClient.service;

import com.userService.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //post rating
    @PostMapping("/ratings")
    Rating createRating(Rating rating);

    //update
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable("ratingId") Long ratingId, Rating rating);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") Long ratingId);
}
