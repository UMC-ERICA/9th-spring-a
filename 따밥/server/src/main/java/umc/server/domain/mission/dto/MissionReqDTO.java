package umc.server.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.PointType;

import java.time.LocalDate;

public class MissionReqDTO {
    public record RegisterDTO(
            @NotNull String description,
            Double point,
            PointType pointType,
            @NotNull LocalDate deadLine,
            @NotNull Long storeId,
            @NotNull String ownerNum
    ){}
}
