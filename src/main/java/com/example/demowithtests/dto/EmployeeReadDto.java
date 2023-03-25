package com.example.demowithtests.dto;

import lombok.ToString;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
public class EmployeeReadDto {

    public String name;
    public String email;
    public Date today = Date.from(Instant.now());
    public Set<PhotoDto> photos = new HashSet<>();
    public Set<AddressDto> addresses = new HashSet<>();

}