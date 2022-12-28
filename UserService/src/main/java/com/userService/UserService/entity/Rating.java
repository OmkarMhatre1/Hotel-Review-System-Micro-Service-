package com.userService.UserService.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;

}
