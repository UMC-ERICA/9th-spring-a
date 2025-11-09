package com.example.jerry.domain.service;

import com.example.jerry.domain.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    List<Review> searchReview(Predicate predicate);
}
