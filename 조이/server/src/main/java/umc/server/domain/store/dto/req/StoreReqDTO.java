package umc.server.domain.store.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class StoreReqDTO {

    @Builder
    public record StoreJoinDTO(
            String name,
            String ceonum
    ) {}
    @Builder
    public record StoreReqAddrDTO(
            String addr1,
            String addr2,
            String addr3,
            String addr4
    ) {}

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class StoreJoinReqDTO{
        private StoreReqDTO.StoreJoinDTO store;
        private StoreReqDTO.StoreReqAddrDTO addr;
    }
}
