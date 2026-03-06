package com.example.jpa.querydsl.helper.bug.repository.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "PERSONS")
@Getter
@Setter
@Immutable
public class PersonRegistrationEntity {
    @Id
    @Column(name = "ID", nullable = false, precision = 10, scale = 0)
    private Long id;

    @Column(name = "PERSON_ID", nullable = false, precision = 8, scale = 0)
    private Long personId;

    @Column(name = "LAST_NAME", nullable = false, length = 200)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = false, length = 200)
    private String firstName;
}
