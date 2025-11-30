package umc.server.domain.store.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.store.converter.StoreAddrConverter;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddr;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPaylaod.code.GeneralErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    //가게 등록
    @Override
    @Transactional
    public StoreResDTO.StoreResponseDTO register(StoreReqDTO.StoreJoinReqDTO dto){
        Store store = StoreConverter.toStore(
                dto.getStore(),
                dto.getAddr() != null ? List.of(dto.getAddr()) : List.of()
        );


        storeRepository.save(store);

        return StoreConverter.toJoinDTO(store);
    }

    @Override
    public StoreResDTO.StoreResponseDTO getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        return StoreConverter.toJoinDTO(store);
    }


}
