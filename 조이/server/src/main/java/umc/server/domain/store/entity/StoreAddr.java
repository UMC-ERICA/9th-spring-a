package umc.server.domain.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;
import umc.server.global.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "StoreAddr")

public class StoreAddr extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Store_id")
    private Store store;

    @Column(name = "addr1",nullable = false)
    private String addr1;

    @Column(name = "addr2",nullable = false)
    private String addr2;

    @Column(name = "addr3",nullable = false)
    private String addr3;

    @Column(name = "addr4",nullable = false)
    private String addr4;

    public void updateStoreAddr( String addr1, String addr2, String addr3, String addr4) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.addr4 = addr4;
    }
}
