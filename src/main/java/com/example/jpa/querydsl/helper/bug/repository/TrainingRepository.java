package com.example.jpa.querydsl.helper.bug.repository;


import com.example.jpa.querydsl.helper.bug.repository.domain.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long>,
        QuerydslPredicateExecutor<TrainingEntity> {
}
