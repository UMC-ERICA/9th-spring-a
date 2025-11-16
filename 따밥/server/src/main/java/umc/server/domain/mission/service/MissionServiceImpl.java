package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.RegisterDTO register(MissionReqDTO.RegisterDTO request) {
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission.MissionBuilder builder = Mission.builder()
                .description(request.description())
                .deadline(request.deadLine())
                .ownerNum(request.ownerNum())
                .store(store);


        if (request.point() != null)
            builder.point(request.point());

        if (request.pointType() != null)
            builder.pointType(request.pointType());

        Mission mission = builder.build();
        missionRepository.save(mission);

        return MissionConverter.toRegisterDTO(mission);
    }
}
