package com.hotelservice.service;

import com.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    //get list of hotels
    List<Hotel> getAllHotels();

    //register hotel
    Hotel registerHotel(Hotel hotel);

    //find hotel by ID
    Hotel getHotel(int id);

    //update hotel
    Hotel updateHotel(Hotel hotel);

    //delete hotel
    String deleteHotel(int id);

}
