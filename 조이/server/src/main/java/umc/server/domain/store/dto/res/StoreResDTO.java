package umc.server.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class StoreResDTO {
    @Builder
    public record StoreResponseDTO(
            Long storeId,
            List<StoreAddResDTO> addr
    ) { }

    @Builder
    public record StoreAddResDTO(
            String addr1,
            String addr2,
            String addr3,
            String addr4
    ) {
    }
}
