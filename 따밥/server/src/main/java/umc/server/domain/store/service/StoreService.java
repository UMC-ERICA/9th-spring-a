package umc.server.domain.store.service;

import umc.server.domain.store.dto.req.StoreReqDTO;

public interface StoreService {
    void register(StoreReqDTO.RegisterDTO request);
    void updateScore(Long storeId, Double score);
}
