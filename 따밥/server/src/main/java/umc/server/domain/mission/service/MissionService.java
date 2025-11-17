package umc.server.domain.mission.service;

import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;

public interface MissionService {
    MissionResDTO.RegisterDTO register(MissionReqDTO.RegisterDTO request);
}
