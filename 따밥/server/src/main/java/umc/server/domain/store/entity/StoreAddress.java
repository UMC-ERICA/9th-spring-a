package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store_address")
public class StoreAddress {
    @Id
    @Column(name = "store_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "addr1", nullable = false)
    private String addr1;

    @Column(name = "addr2", nullable = false)
    private String addr2;

    @Column(name = "addr3", nullable = false)
    private String addr3;

    @Column(name = "addr4", nullable = false)
    private String addr4;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longtitude", nullable = false)
    private Double longtitude;

    public void updateAddress(String addr1, String addr2, String addr3, String addr4,  Double latitude, Double longtitude) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.addr4 = addr4;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
