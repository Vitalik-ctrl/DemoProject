package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;

public class AddressDto {

    private Long id;
    private Boolean addressHasActive = Boolean.TRUE;
    private String country;
    private String city;
    private String street;
    public Date date = Date.from(Instant.now());
}
