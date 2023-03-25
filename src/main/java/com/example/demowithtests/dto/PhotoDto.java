package com.example.demowithtests.dto;

import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@ToString
public class PhotoDto {

    public String photoLink;
    public Double width;
    public Double height;
    public Date dateCreated = Date.from(Instant.now());
    public Boolean isVisible = Boolean.TRUE;

}
