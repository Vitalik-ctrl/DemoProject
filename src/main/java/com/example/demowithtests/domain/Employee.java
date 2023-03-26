package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String email;
    private Boolean isDeleted = Boolean.FALSE;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Photo> photos = new HashSet<>();

    public Employee(String name, String country, String email, Boolean isDeleted) {
        this.name = name;
        this.country = country;
        this.email = email;
        this.isDeleted = isDeleted;
    }
}
