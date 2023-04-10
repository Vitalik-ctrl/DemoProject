package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.workplace.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;


@ToString
public class WorkplaceResponseDto {

    @Schema(description = "Name.", example = "Weak")
    public String name;
    @Schema(description = "Address.", example = "Weak")
    public String address;
    @Schema(description = "Capacity of workplace.", example = "4")
    public Integer capacity;
    @Schema(description = "State of workplace.", example = "GOOD")
    public State state;
    @Schema(description = "Creation date of workplace.", example = "21.12.2003")
    public Date dateCreated;
    @Schema(description = "Activity of workplace.", example = "true")
    public Boolean isActive;
}