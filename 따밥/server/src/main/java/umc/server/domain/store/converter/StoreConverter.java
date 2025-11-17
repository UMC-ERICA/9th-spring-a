package umc.server.domain.store.converter;

import umc.server.domain.member.entity.Food;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddress;

public class StoreConverter {
    public static Store toStore(StoreReqDTO.RegisterDTO dto, Food food) {
        return Store.builder()
                .storeName(dto.storeName())
                .food(food)
                .openingTime(dto.openingTime())
                .closingTime(dto.closingTime())
                .breakStartTime(dto.breakStartTime())
                .breakEndTime(dto.breakEndTime())
                .build();
    }

    public static StoreAddress toStoreAddress(StoreReqDTO.RegisterDTO dto){
        return StoreAddress.builder()
                .addr1(dto.addr1())
                .addr2(dto.addr2())
                .addr3(dto.addr3())
                .addr4(dto.addr4())
                .latitude(dto.latitude())
                .longtitude(dto.longitude())
                .build();
    }

    public static StoreResDTO.RegisterDTO toRegisterDTO(Store store){
        return StoreResDTO.RegisterDTO.builder()
                .storeId(store.getId())
                .build();
    }
}
