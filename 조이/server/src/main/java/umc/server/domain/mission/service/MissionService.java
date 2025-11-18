package umc.server.domain.mission.service;

import umc.server.domain.mission.dto.res.MissionResDTO;

public interface MissionService {
    public MissionResDTO.MissionRes getMission(Long memberId);
}
