package umc.server.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.exception.FoodErrorCode;
import umc.server.domain.member.exception.FoodException;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddress;
import umc.server.domain.store.exception.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreAddressRepository;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;
    private final StoreAddressRepository storeAddressRepository;

    @Override
    public StoreResDTO.RegisterDTO register(StoreReqDTO.RegisterDTO request) {
        Food food = foodRepository.findById(request.foodId())
                .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND));

        Store store = StoreConverter.toStore(request, food);
        StoreAddress storeAddress = StoreConverter.toStoreAddress(request);

        storeRepository.save(store);

        storeAddress.updateStore(store);
        storeAddressRepository.save(storeAddress);

        return StoreConverter.toRegisterDTO(store);
    }

    @Override
    public void updateScore(Long storeId, Double score) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        store.updateScore(score);
    }
}
