package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;

public class MissionConverter {
    public static MissionResDTO.RegisterDTO toRegisterDTO(Mission mission){
        return MissionResDTO.RegisterDTO.builder()
                .storeId(mission.getStore().getId())
                .deadLine(mission.getDeadline())
                .build();
    }
}
