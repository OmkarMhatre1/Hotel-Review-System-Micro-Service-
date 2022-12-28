package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService service;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = service.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> allRating = service.getAllRating();
        return ResponseEntity.ok(allRating);
    }
    //get rating by hotel id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId){
        List<Rating> ratingByUserId = service.getRatingByUserId(userId);
        return ResponseEntity.ok(ratingByUserId);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId){
        List<Rating> ratingByHotelIdId = service.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratingByHotelIdId);
    }



}
