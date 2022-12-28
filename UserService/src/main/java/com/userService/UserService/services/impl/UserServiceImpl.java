package com.userService.UserService.services.impl;

import com.userService.UserService.entity.Hotel;
import com.userService.UserService.entity.Rating;
import com.userService.UserService.entity.User;
import com.userService.UserService.exceptions.ResourceNotFoundException;
import com.userService.UserService.feignClient.service.HotelService;
import com.userService.UserService.repositories.userRepository;
import com.userService.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    //feign client
    @Autowired
    private HotelService hotelService;



    @Override
    public User saveUser(User user) {
        //generate unique user id
        //String randomUserId = UUID.randomUUID().toString();
        //user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with give id is not found on server !! :" + userId));
        //fetch rating of user from RATING SERVICE
        //http://localhost:8082/ratings/users/152
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList=ratings.stream().map(rating -> {
            //  System.out.println(rating.getHotelId());

            //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            //feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public String deleteUser(int userId) {
        userRepository.deleteById(userId);
        return "user deleted"+userId;
    }

    @Override
    public User updateUser(User user) {
        User user1=userRepository.findById(user.getUserId()).orElse(null);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAbout(user.getAbout());
        return userRepository.save(user1);
    }
}
