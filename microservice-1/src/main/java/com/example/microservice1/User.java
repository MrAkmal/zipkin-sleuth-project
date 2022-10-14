package com.example.microservice1;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;

    private String username;

    private int age;

    private String phoneNumber;

    private String gender;


}
