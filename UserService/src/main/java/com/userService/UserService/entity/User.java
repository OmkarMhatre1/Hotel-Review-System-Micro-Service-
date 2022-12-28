package com.userService.UserService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private int userId;
        @Column(name = "NAME")
        private String name;
        @Column(name = "EMAIL")
        private String email;
        @Column(name = "ABOUT")
        private String about;

        @Transient
        private List<Rating>  ratings=new ArrayList<>();



}
