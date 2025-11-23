package umc.server.domain.store.converter;

import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.entity.Store;

import java.util.Collections;
import java.util.List;

import static umc.server.domain.member.enums.Type.store;

public class StoreConverter {
    //entity -> dto
    public static StoreResDTO.StoreResponseDTO toJoinDTO(Store store) {
        List<StoreResDTO.StoreAddResDTO> addrs = store.getStoreAddr().stream()
                .map(addr -> new StoreResDTO.StoreAddResDTO(
                        addr.getAddr1(),
                        addr.getAddr2(),
                        addr.getAddr3(),
                        addr.getAddr4()))
                .toList();

        return new StoreResDTO.StoreResponseDTO(store.getId(), addrs);
    }

    //dto -> entity
    public static Store toStore(
            StoreReqDTO.StoreJoinDTO dto,
            List<StoreReqDTO.StoreReqAddrDTO> addrDtos
    ){
        Store store= Store.builder()
                .name(dto.name())
                .ceosnum(dto.ceonum())
                .build();
        // null-safe 처리: addrDtos가 null이면 빈 리스트로 처리
        (addrDtos != null ? addrDtos : Collections.<StoreReqDTO.StoreReqAddrDTO>emptyList())
                .forEach(addrDto -> store.addStoreAddr(StoreAddrConverter.toStoreAddr(addrDto)));

        return store;

}}



