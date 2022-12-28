package com.hotelservice.service.impl;

import com.hotelservice.entities.Hotel;
import com.hotelservice.exceptions.ResourceNotFoundException;
import com.hotelservice.reposiotries.HotelRepository;
import com.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository repository;

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public Hotel registerHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    @Override
    public Hotel getHotel(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        Hotel hotel1=repository.findById(hotel.getId()).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found"));
        hotel1.setName(hotel.getName());
        hotel1.setLocation(hotel.getLocation());
        hotel1.setAbout(hotel.getAbout());
        return repository.save(hotel1);
    }

    @Override
    public String deleteHotel(int id) {
        repository.deleteById(id);
        return "Hotel Deleted"+id;
    }
}
