package com.example.jpa.querydsl.helper.bug.repository.impl;


import com.example.jpa.querydsl.helper.bug.repository.CommonViewRepository;
import com.example.jpa.querydsl.helper.bug.repository.domain.CommonFilter;
import com.example.jpa.querydsl.helper.bug.repository.domain.Subscription;
import com.example.jpa.querydsl.helper.bug.repository.domain.SubscriptionEntity;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.jpa.querydsl.helper.bug.repository.domain.QPersonRegistrationEntity.personRegistrationEntity;
import static com.example.jpa.querydsl.helper.bug.repository.domain.QSubscriptionEntity.subscriptionEntity;
import static com.example.jpa.querydsl.helper.bug.repository.domain.QTrainingEntity.trainingEntity;

@Repository
public class SubscriptionRepositoryImpl extends QuerydslRepositorySupport implements CommonViewRepository<Subscription> {
    protected final EntityManager em;
    private final EntityPathBase<?> entityPathBase;

    public SubscriptionRepositoryImpl(EntityManager em) {
        super(SubscriptionEntity.class);
        this.em = em;
        entityPathBase = subscriptionEntity;
    }

    public Expression<Subscription> getProjection() {
        return Projections.constructor(Subscription.class,
                subscriptionEntity.id.as("id"),
                personRegistrationEntity.personId.as("personId"),
                personRegistrationEntity.lastName.as("lastName"),
                personRegistrationEntity.firstName.as("firstName"),
                trainingEntity.courseCode.as("courseCode"),
                subscriptionEntity.remark.as("remark")
        );
    }

    public <T> JPAQuery<T> getQuery(Expression<T> selectList, @Nullable Predicate predicate) {
        return new JPAQuery<T>(em)
                .select(selectList)
                .from(entityPathBase)
                .where(predicate)
                .innerJoin(subscriptionEntity.training, trainingEntity)
                .innerJoin(subscriptionEntity.registration, personRegistrationEntity);
    }

    public Predicate mapToPredicate(CommonFilter f) {
        return null;
    }

    @Override
    public Page<Subscription> findAllByFilter(CommonFilter filter, Pageable pageable) {
        Long totalCount = getQuery(getCountExpression(), mapToPredicate(filter)).fetch().stream().findFirst().orElseThrow();

        JPAQuery<Subscription> query = getQuery(getProjection(), mapToPredicate(filter));
        Optional.ofNullable(getQuerydsl()).ifPresent(querydsl -> querydsl.applyPagination(pageable, query));
        List<Subscription> pagedData = query.fetch();
        return PageableExecutionUtils.getPage(pagedData, pageable, () -> totalCount);
    }

    public NumberExpression<Long> getCountExpression() {
        return entityPathBase.count();
    }
}

