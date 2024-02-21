package com.example.demowithtests.dto.workplace;

import com.example.demowithtests.domain.workplace.State;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class WorkplaceRequestDto {
    @Schema(description = "Id of workplace.", example = "12")
    public Integer id;
    @Schema(description = "Name of workplace.", example = "Weak")
    public String name;
    @Schema(description = "Address of workplace.", example = "St.34a/2")
    public String address;
    @Schema(description = "Capacity of workplace.", example = "4")
    public Integer capacity;
    @Schema(description = "State of workplace.", example = "GOOD")
    public final State state = State.NORMAL;
    @Schema(description = "Creation date of workplace.", example = "12.12.2012")
    public Date dateCreated = Date.from(Instant.now());
    @Schema(description = "Activity of workplace.", example = "true")
    public Boolean isActive = Boolean.TRUE;
}