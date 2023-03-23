package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;

public class EmployeeReadDto {

    public String name;
 //   public String country;
    public String email;

    public Date today = Date.from(Instant.now());

    @Override
    public String toString() {
        return "EmployeeReadDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", today=" + today +
                '}';
    }
}