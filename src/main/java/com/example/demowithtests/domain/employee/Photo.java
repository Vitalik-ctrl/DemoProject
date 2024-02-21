package com.example.demowithtests.domain.employee;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String photoLink;
    private Double width;
    private Double height;
    private Date dateCreated = Date.from(Instant.now());

    private Boolean isVisible = Boolean.TRUE;

}
