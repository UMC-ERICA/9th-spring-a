package umc.server.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResDTO {
    @Getter
    @Builder
    public static class MissionInfo{
        private Long missionId;
        private String description;
        private Double point;
        private LocalDate deadLine;
        private String storeName;
    }

    @Builder
    public record RegisterDTO(
            Long storeId,
            LocalDate deadLine
    ){}
}
