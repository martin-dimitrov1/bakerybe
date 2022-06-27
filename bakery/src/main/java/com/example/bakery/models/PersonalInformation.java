package com.example.bakery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonalInformation {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    //true = phone , false = email
    private Boolean phoneOrEmail;
}
