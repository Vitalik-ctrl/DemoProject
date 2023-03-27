package com.example.demowithtests.dto.passport;

import lombok.ToString;

import java.time.LocalDate;

@ToString
public class PassportRequestDto {

    public String firstName;
    public String secondName;
    public LocalDate birthDate;

}
