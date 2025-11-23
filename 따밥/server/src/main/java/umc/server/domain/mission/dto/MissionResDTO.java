package umc.server.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record MissionPreviewList(
       List<MissionInfo> missionList,
       Integer listSize,
       Integer totalPage,
       Long totalElements,
       Boolean isFirst,
       Boolean isLast
    ){}

    @Builder
    public record MissionInfo(
        Long missionId,
        String description,
        Double point,
        LocalDate deadLine,
        String storeName
    ){}

    @Builder
    public record RegisterDTO(
            Long storeId,
            LocalDate deadLine
    ){}
}
