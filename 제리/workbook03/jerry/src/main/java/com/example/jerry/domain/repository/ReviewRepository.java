package com.example.jerry.domain.repository;

import com.example.jerry.domain.entity.Review;
import com.example.jerry.domain.service.ReviewQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 on s1.location_id = l1.id " +
                    "WHERE l1.name LIKE CONCAT('%', :name, '%')",
            nativeQuery = true
    )
    List<Review> searchReviewByLocation(@Param("name") String name);

    @Query(
            value = "SELECT r1.* " +
                    "FROM review r1 " +
                    "LEFT JOIN store s1 ON r1.store_id = s1.id " +
                    "LEFT JOIN location l1 on s1.location_id = l1.id " +
                    "WHERE l1.name LIKE CONCAT('%', :name, '%') " +
                    "AND r1.star > :star",
            nativeQuery = true
    )
    List<Review> searchReviewByLocation(
            @Param("name") String name,
            @Param("star") Float star
    );


    Page<Review> findByMember_MemberId(Long memberId, Pageable pageable);
}
