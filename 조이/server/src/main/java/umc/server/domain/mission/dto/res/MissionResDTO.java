package umc.server.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionResDTO {
    @Getter
    @Builder

public static class MissionRes {
    private String storeName;
    private String title;
    private String mcontext;
    private LocalDate mlimit;
    private Integer finishscore;
    }
}
