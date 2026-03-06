package com.example.jpa.querydsl.helper.bug.repository;


import com.example.jpa.querydsl.helper.bug.repository.domain.Subscription;
import com.example.jpa.querydsl.helper.bug.repository.domain.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long>,
        CommonViewRepository<Subscription>,
        QuerydslPredicateExecutor<SubscriptionEntity> {
}
