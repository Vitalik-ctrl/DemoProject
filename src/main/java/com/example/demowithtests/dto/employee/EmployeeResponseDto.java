package com.example.demowithtests.dto.employee;

import com.example.demowithtests.dto.passport.PassportResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
public class EmployeeResponseDto {

    @Schema(description = "Name of an employee.", example = "Billy")
    public String name;
    @Schema(description = "Email of an employee.", example = "billy.a@gmail.com")
    public String email;
    @Schema(description = "Photos of an employee.", example = "photos")
    public Set<PhotoDto> photos = new HashSet<>();
    @Schema(description = "Addresses of an employee.", example = "addresses")
    public Set<AddressDto> addresses = new HashSet<>();
    public Date today = Date.from(Instant.now());
    public PassportResponseDto passport;



}