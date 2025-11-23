package umc.server.domain.store.converter;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddr;

public class StoreAddrConverter{
    public static StoreAddr toStoreAddr(StoreReqDTO.StoreReqAddrDTO dto){
        return StoreAddr.builder()
                .addr1(dto.addr1())
                .addr2(dto.addr2())
                .addr3(dto.addr3())
                .addr4(dto.addr4())
                .build();
    }
}
