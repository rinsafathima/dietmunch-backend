package com.dietmunch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class
AuthUserDTO {

    private int id;

    private String name;

    private String email;

    private String mobileNumber;

    private String address;

    private String role;

    private int age;

}
