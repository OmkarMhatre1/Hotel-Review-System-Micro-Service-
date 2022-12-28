package com.userService.UserService;

import com.userService.UserService.feignClient.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//		Rating rating=Rating.builder().rating(10).userId(5L).hotelId(8L).feedback("feign clinet").build();
//		Rating saveRating = ratingService.createRating(rating);
//		System.out.println("new rating created");
//
//	}

}
