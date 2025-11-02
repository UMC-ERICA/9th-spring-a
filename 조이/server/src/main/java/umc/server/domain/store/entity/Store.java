package umc.server.domain.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.review.entity.Review;
import umc.server.global.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "Store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ceosnum", length = 9, nullable = false)
    private Integer ceosnum;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_id") // FK
    private Review review ;

}
