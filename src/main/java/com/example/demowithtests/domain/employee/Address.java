package com.example.demowithtests.domain.employee;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Data
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