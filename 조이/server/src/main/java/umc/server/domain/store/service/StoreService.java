package umc.server.domain.store.service;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.StoreAddr;

public interface StoreService {
    StoreResDTO.StoreResponseDTO register(
            StoreReqDTO.StoreJoinReqDTO dto
    );
    StoreResDTO.StoreResponseDTO getStore(Long storeId);
}