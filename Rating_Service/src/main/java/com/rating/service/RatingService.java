package com.rating.service;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getAllRating();

    //get all by UserId
    List<Rating> getRatingByUserId(Long userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(Long hotelId);

}
