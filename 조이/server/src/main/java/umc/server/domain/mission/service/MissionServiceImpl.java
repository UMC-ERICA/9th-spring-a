package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.repository.MissionRepository;

import static umc.server.domain.member.enums.Type.mission;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionRes getMission(Long memberId){
        return MissionResDTO.MissionRes.builder()
                .storeName("가게이름")
                .finishscore(500)
                .build();
    }
}
