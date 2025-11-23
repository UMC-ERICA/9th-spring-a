package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.Term;
import umc.server.domain.review.entity.Review;
import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long>{

}