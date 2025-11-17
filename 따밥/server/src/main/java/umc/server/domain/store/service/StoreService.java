package umc.server.domain.store.service;

import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;

public interface StoreService {
    StoreResDTO.RegisterDTO register(StoreReqDTO.RegisterDTO request);
    void updateScore(Long storeId, Double score);
}
