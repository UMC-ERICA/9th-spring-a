package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.ReviewQueryDsl;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    @Query("SELECT r FROM Review r " +
    "JOIN FETCH r.store s " +
    "WHERE r.member.id = :memberId " +
    "ORDER BY r.createdAt DESC")
    List<Review> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT r FROM Review r JOIN FETCH r.member WHERE r.store.id = :storeId")
    List<Review> findByStoreIdWithMember(@Param("storeId") Long storeId);

    // 2025.11.02 리뷰 검색 기능 추가
    // 지역 조회
    @Query("SELECT r FROM Review r " +
            "JOIN r.store s " +
            "JOIN s.storeAddress sa " +
            "WHERE (sa.addr1 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr2 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr3 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr4 LIKE CONCAT('%', :addr, '%')) " +
            "ORDER BY r.createdAt DESC ")
    List<Review> findByAddr(@Param("addr") String addr);
    
    // 별점 조회
    List<Review> findByScoreOrderByCreatedAtDesc(Double score);
    
    // 지역 + 별점 조회
    @Query("SELECT r FROM Review r " +
            "JOIN r.store s " +
            "JOIN s.storeAddress sa " +
            "WHERE (sa.addr1 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr2 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr3 LIKE CONCAT('%', :addr, '%') " +
            "OR sa.addr4 LIKE CONCAT('%', :addr, '%')) " +
            "AND r.score >= :score AND r.score < :score + 1 " +
            "ORDER BY r.createdAt DESC")
    List<Review> findByAddrAndScore(@Param("addr") String addr, @Param("score") int score);

    // 특정한 store에 별점을 남긴 사람들의 평균
    @Query("SELECT AVG (r.score) FROM Review r WHERE r.store.id = :storeId")
    Double findAvgByStoreId(@Param("storeId") Long storeId);
}
