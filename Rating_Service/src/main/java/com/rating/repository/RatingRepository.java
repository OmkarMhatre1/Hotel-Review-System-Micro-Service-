package com.rating.repository;

import com.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {


    List<Rating> findByUserId(Long userId);

    List<Rating> findByHotelId(Long hotelId);
}
