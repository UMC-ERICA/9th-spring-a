package umc.server.domain.mission.dto.req;

import umc.server.domain.mission.entity.Mission;

import java.time.LocalDate;

public class MissionReqDTO {
    public record MissionRequestDTO(
            String title,
            String mcontext,
            LocalDate mlimit,
            Float finishscore
    ) {}



}
