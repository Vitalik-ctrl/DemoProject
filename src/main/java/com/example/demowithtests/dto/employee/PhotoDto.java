package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class PhotoDto {

    @Schema(description = "Link to photo.", example = "asw.com/link")
    public String photoLink;
    @Schema(description = "Width of a photo.", example = "34")
    public Double width;
    @Schema(description = "Height of a photo.", example = "24")
    public Double height;
    @Schema(description = "Date of a photo creating.", example = "2023-03-25")
    public Date dateCreated = Date.from(Instant.now());
    @Schema(description = "Visibility of a photo.", example = "True")
    public Boolean isVisible = Boolean.TRUE;

}
