package umc.server.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddress;
import umc.server.domain.store.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;

    @Override
    public void register(StoreReqDTO.RegisterDTO request) {
        Optional<Food> food = foodRepository.findById(request.getFoodId());

        Store store = Store.builder()
                .storeName(request.getStoreName())
                .food(food.get())
                .openingTime(request.getOpeningTime())
                .closingTime(request.getClosingTime())
                .breakStartTime(request.getBreakStartTime())
                .breakEndTime(request.getBreakEndTime())
                .build();

        StoreReqDTO.StoreAddress requestAddress = request.getAddress();
        StoreAddress storeAddress = StoreAddress.builder()
                .addr1(requestAddress.getAddr1())
                .addr2(requestAddress.getAddr2())
                .addr3(requestAddress.getAddr3())
                .addr4(requestAddress.getAddr4())
                .latitude(requestAddress.getLatitude())
                .longtitude(requestAddress.getLongitude())
                .build();

        store.changeStoreAddress(storeAddress);

        storeRepository.save(store);
    }
}
