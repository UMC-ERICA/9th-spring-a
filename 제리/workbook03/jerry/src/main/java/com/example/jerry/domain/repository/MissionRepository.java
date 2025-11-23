package com.example.jerry.domain.repository;

import com.example.jerry.domain.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.restaurant r " +
            "JOIN Address a ON a.id = r.address.id " +
            "WHERE a.address LIKE %:region% " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM MemberMission mm " +
            "    WHERE mm.mission.id = m.id " +
            "    AND mm.member.id = :memberId" +
            ") " +
            "ORDER BY m.missionDate ASC")
    List<Mission> findAvailableMissionsInRegion(
            @Param("memberId") Long memberId,
            @Param("region") String region,
            Pageable pageable
    );

    Page<Mission> findByRestaurant_RestaurantsId(Integer restaurantId, Pageable pageable);
}