package com.example.jpa.querydsl.helper.bug.repository.domain;

public record Subscription(Long id,
                           Long personId,
                           String lastName,
                           String firstName,
                           String courseCode,
                           String remark) {
}
