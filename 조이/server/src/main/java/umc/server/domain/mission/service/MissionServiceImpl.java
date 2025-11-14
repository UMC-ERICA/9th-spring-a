package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.dto.res.MissionResDTO;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    @Override
    public MissionResDTO.MissionRes getMission(Long memberId){
        return MissionResDTO.MissionRes.builder()
                .storeName("가게이름")
                .finishscore(500)
                .build();


}}
