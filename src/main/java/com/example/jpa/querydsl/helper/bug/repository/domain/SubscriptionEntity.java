package com.example.jpa.querydsl.helper.bug.repository.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SUBSCRIPTIONS")
@Getter
@Setter
public class SubscriptionEntity {
    @Id
    @Column(name = "ID", nullable = false, precision = 10)
    private Long id;

    @Column(name = "TRAINING_ID", nullable = false, precision = 10)
    private Long trainingId;

    @Column(name = "REGISTRATION_ID", nullable = false, precision = 10)
    private Long registrationId;

    @Column(name = "REMARK", length = 2000)
    private String remark;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINING_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    private TrainingEntity training;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    private PersonRegistrationEntity registration;
}
