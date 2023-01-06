package com.userService.UserService.controllers;

import com.userService.UserService.entity.User;
import com.userService.UserService.services.UserService;
import com.userService.UserService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    private UserService userService;




    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "CktBreaker",fallbackMethod = "ratingHotelFallback")
    //for retry
    // @Retry(name = "CktBreaker",fallbackMethod = "ratingHotelFallback")
    //Rate Limiter
    //@RateLimiter(name = "CktBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable int userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //fallback method using circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(int userId, Exception ex){
       // System.out.println("Service is down");
        User user = User.builder()
                .email("dummy email")
                .name("dummy")
                .about("this user is created because user is down")
                .userId(1213)
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getListOfUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User user1 = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

}
