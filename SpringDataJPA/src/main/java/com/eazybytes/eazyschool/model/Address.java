package com.eazybytes.eazyschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5 , message = "Address1 must be at least 5 Characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min = 3 , message = "City must be at least 5 Characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 5 , message = "State must be at least 5 Characters long")
    private String state;

    @NotBlank(message = "Zipcode must not be blank")
    @Pattern(regexp = "^|[0-9]{5}", message = "zip must be at least 5 digits")
    private String zipcode;
}
