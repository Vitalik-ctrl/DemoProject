package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class AddressDto {

    @Schema(description = "Address activity.", example = "True")
    public Boolean addressHasActive = Boolean.TRUE;
    @Schema(description = "Address country.", example = "Ukraine")
    public String country;
    @Schema(description = "Address city.", example = "Odesa")
    public String city;
    @Schema(description = "Address street.", example = "Akademska")
    public String street;
    @Schema(description = "Date address was added.", example = "2023-03-25")
    public Date date = Date.from(Instant.now());

}
