package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class PhotoDto {

    @Schema(description = "Link to photo.", example = "asw.com/link", required = true)
    public String photoLink;
    @Schema(description = "Width of a photo.", example = "34", required = true)
    public Double width;
    @Schema(description = "Height of a photo.", example = "24", required = true)
    public Double height;
    @Schema(description = "Date of a photo creating.", example = "2023-03-25", required = true)
    public Date dateCreated = Date.from(Instant.now());
    @Schema(description = "Visibility of a photo.", example = "True", required = true)
    public Boolean isVisible = Boolean.TRUE;

}
