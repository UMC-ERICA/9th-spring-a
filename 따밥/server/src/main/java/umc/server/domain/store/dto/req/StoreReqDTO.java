package umc.server.domain.store.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

public class StoreReqDTO {
    @Builder
    @Getter
    public static class RegisterDTO{
        private String storeName;
        private Long foodId;
        private LocalTime openingTime;
        private LocalTime closingTime;
        private LocalTime breakStartTime;
        private LocalTime breakEndTime;
        private StoreAddress address;
    }

    @Builder
    @Getter
    public static class StoreAddress {
        private String addr1;
        private String addr2;
        private String addr3;
        private String addr4;
        private Double latitude;
        private Double longitude;
    }
}
