package com.example.jpa.querydsl.helper.bug.repository;

import com.example.jpa.querydsl.helper.bug.repository.domain.PersonRegistrationEntity;
import com.example.jpa.querydsl.helper.bug.repository.domain.Subscription;
import com.example.jpa.querydsl.helper.bug.repository.domain.SubscriptionEntity;
import com.example.jpa.querydsl.helper.bug.repository.domain.TrainingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SubscriptionRepositoryImplTest {
    public static final long TRAINING_ID = 190L;
    public static final long REGISTRATION_ID = 1L;

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    PersonRegistrationRepository personRegistrationRepository;
    @Autowired
    TrainingRepository trainingRepository;

    @BeforeEach
    void setup() {
        PersonRegistrationEntity registration = new PersonRegistrationEntity();
        registration.setId(REGISTRATION_ID);
        registration.setPersonId(15L);
        registration.setLastName("Rogers");
        registration.setFirstName("Peter");
        personRegistrationRepository.save(registration);

        TrainingEntity training = new TrainingEntity();
        training.setId(TRAINING_ID);
        training.setCourseCode("XYZ");
        training.setPublicationDate(LocalDate.now());
        trainingRepository.save(training);

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setId(89L);
        subscription.setRegistrationId(REGISTRATION_ID);
        subscription.setTrainingId(TRAINING_ID);
        subscription.setRemark("First subscription");
        subscriptionRepository.save(subscription);
    }

    @Test
    void findAll() {
        Page<Subscription> inschrijvingen = subscriptionRepository.findAllByFilter(null,
                PageRequest.of(0, 10, Sort.by("registration.firstName").ascending()));
        assertThat(inschrijvingen).hasSize(1);

        inschrijvingen = subscriptionRepository.findAllByFilter(null,
                PageRequest.of(0, 10, Sort.by("firstName").ascending()));
        // Results in: org.springframework.data.core.PropertyReferenceException: No property 'firstName' found for type 'SubscriptionEntity'

        assertThat(inschrijvingen).hasSize(1);
    }
}