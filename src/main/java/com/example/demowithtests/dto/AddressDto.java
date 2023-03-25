package com.example.demowithtests.dto;

import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class AddressDto {

    public Boolean addressHasActive = Boolean.TRUE;
    public String country;
    public String city;
    public String street;
    public Date date = Date.from(Instant.now());

}
