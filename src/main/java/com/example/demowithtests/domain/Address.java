package com.example.demowithtests.domain;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Builder
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean addressHasActive = Boolean.TRUE;
    private String country;
    private String city;
    private String street;
    private Date date = Date.from(Instant.now());

}