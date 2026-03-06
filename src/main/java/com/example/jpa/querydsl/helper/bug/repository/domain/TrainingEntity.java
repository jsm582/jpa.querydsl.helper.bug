package com.example.jpa.querydsl.helper.bug.repository.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TRAININGS")
@Getter
@Setter
public class TrainingEntity {
    @Id
    @Column(name = "ID", updatable = false, insertable = false, precision = 10)
    private Long id;

    @Column(name = "COURSE_CODE", nullable = false, length = 50)
    private String courseCode;

    @Column(name = "PUBLICATION_DATE", nullable = false)
    private LocalDate publicationDate;
}
