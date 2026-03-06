package com.example.jpa.querydsl.helper.bug.repository;


import com.example.jpa.querydsl.helper.bug.repository.domain.CommonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonViewRepository<V> {
    Page<V> findAllByFilter(CommonFilter filter, Pageable pageable);
}