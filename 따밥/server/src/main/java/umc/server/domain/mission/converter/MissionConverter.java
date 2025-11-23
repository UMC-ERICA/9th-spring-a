package umc.server.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;

public class MissionConverter {
    public static MissionResDTO.RegisterDTO toRegisterDTO(Mission mission){
        return MissionResDTO.RegisterDTO.builder()
                .storeId(mission.getStore().getId())
                .deadLine(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPreviewList toMissionPreviewList(Page<Mission> result){
        return MissionResDTO.MissionPreviewList.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionInfo)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionInfo toMissionInfo(Mission mission){
        return MissionResDTO.MissionInfo.builder()
                .missionId(mission.getId())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadLine(mission.getDeadline())
                .storeName(mission.getStore().getStoreName())
                .build();
    }
}
