package umc.server.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.store.entity.StoreAddress;

import java.time.LocalTime;

public class StoreReqDTO {
    public record RegisterDTO(
        @NotBlank String storeName,
        Long foodId,
        @NotNull LocalTime openingTime,
        @NotNull LocalTime closingTime,
        @NotNull LocalTime breakStartTime,
        @NotNull LocalTime breakEndTime,
        String addr1,
        String addr2,
        String addr3,
        String addr4,
        Double latitude,
        Double longitude
    ){}
}
