package umc.server.global;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //자식 테이블에 필드가 생성되진 않지만, 상속받을수 있게 해줌
@EntityListeners(AuditingEntityListener.class) //엔티티가 생성될때 자동으로 날짜나 정보를 기록
@Getter // public String getName() {
        // return name;} 이거 원래 만들어줘야 하는데 자동으로 맹드러줘


public class BaseEntity {
    @CreatedDate

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
