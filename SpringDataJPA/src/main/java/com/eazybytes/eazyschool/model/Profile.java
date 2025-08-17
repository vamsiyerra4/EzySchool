package com.eazybytes.eazyschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 5, message = "Please provide complete address")
    private String address1;

    private String address2;

    @NotBlank
    @Size(min = 5, message = "city must be at least 5 characters long")
    private String city;

    @NotBlank
    @Size(min = 3,message = "Provide complete name")
    private String state;

    @NotBlank(message="Zip Code must not be blank")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
    private String zipCode;

}
