package com.hotelservice.controllers;

import com.hotelservice.entities.Hotel;
import com.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService service;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> allHotels = service.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Hotel> registerHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = service.registerHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable int id){
        Hotel hotel = service.getHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = service.updateHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

}
