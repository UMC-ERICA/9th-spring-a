package com.example.jerry.domain.repository;

import com.example.jerry.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberId);
    List<Review> findByRestaurant(Byte restaurant);
}