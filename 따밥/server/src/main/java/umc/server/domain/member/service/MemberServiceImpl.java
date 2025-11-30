package umc.server.domain.member.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberAddress;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.exception.FoodErrorCode;
import umc.server.domain.member.exception.FoodException;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberAddressRepository;
import umc.server.domain.member.repository.MemberFoodRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.exception.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
<<<<<<< Updated upstream
=======
import umc.server.global.auth.CustomUserDetails;
import umc.server.global.auth.JwtUtil;
import umc.server.global.auth.enums.Role;
>>>>>>> Stashed changes

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final MissionRepository missionRepository;

<<<<<<< Updated upstream
=======
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

>>>>>>> Stashed changes
    @Override
    public Member findByUsername(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public MemberResDTO.JoinDTO join(MemberReqDTO.JoinDTO request) {
        validateRequest(request);

        Member member = MemberConverter.toMember(request);
        memberRepository.save(member);

        // 주소 저장
        MemberAddress memberAddress = MemberConverter.toMemberAddress(member, request);
        memberAddressRepository.save(memberAddress);

        // 좋아하는 음식 저장
        if (request.preferFood().size() > 1){
            List<MemberFood> memberFood = request.preferFood().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(
                                            () -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND)))
                            .build()
                    )
                    .toList();

            // 모든 선호 음식 추가
            memberFoodRepository.saveAll(memberFood);
        }

        return MemberConverter.toJoinDTO(member);
    }

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.PASSWORD_INVALID);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDTO(member, accessToken);
    }

    @Override
    public MemberResDTO.HomeTopDTO getHomeTop(Long memberId, String region, Long cursor, int size) {
        Member member = findByUsername(memberId);

        Integer totalMissionCount = memberMissionRepository
                .countInProgressMissions(memberId, false);

        Pageable pageable = PageRequest.of(0, size + 1);
        List<MemberMission> missions;

        if (cursor == null){
            cursor = 0L;
            missions = memberMissionRepository
                    .findInProgressMissionsFirstPage(memberId, false, pageable);
        } else {
            missions = memberMissionRepository
                    .findInProgressMissionsWithCursor(memberId, false, cursor, pageable);
        }

        boolean hasNext = missions.size() > size;

        if (hasNext)
            missions = missions.subList(0, size);

        Long nextCursor = missions.isEmpty() ? null : missions.get(missions.size() - 1).getId();

        return MemberConverter.toHomeTopDTO(
                member,
                region,
                totalMissionCount,
                missions,
                nextCursor,
                hasNext
        );

    }

    @Override
    public void missionSuccess(Long memberId, Long missionId) {
        MemberMission mm = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_MISSION_NOT_FOUND));

        mm.changeIsCompleted(true);
    }

    @Override
    public MemberResDTO.MyPageDTO getMyPage(Long memberId) {
        Member member =  findByUsername(memberId);

        return MemberConverter.toMyPageDTO(member);
    }



    @Override
    public ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, Integer page) {
        Member member = findByUsername(memberId);
        PageRequest pageRequest = PageRequest.of(page, 5);

        Page<Review> result = reviewRepository.findAllByMemberId(memberId, pageRequest);

        return ReviewConverter.toReviewPreViewListDTO(result);
    }

    @Override
    public MemberResDTO.ChallengeMissionDTO challengeMission(Long memberId, Long missionId) {
        Member member = findByUsername(memberId);
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberConverter.toMemberMission(member, mission);

        memberMissionRepository.save(memberMission);

        return MemberConverter.toChallengeMissionDTO(memberMission);
    }

    @Override
    public MissionResDTO.MissionPreviewList getMyMissions(Long memberId, Integer page) {
        Member member = findByUsername(memberId);
        Pageable pageable = PageRequest.of(page, 5);

        Integer totalCount = memberMissionRepository.countInProgressMissions(memberId, false);
        List<MemberMission> mm = memberMissionRepository.findInProgressMissionsFirstPage(memberId, false, pageable);

        Page<MemberMission> memberMissionPage = new PageImpl<>(mm, pageable, totalCount);

        Page<Mission> missionPage = memberMissionPage.map(MemberMission::getMission);

        return MissionConverter.toMissionPreviewList(missionPage);
    }

    private void validateRequest(MemberReqDTO.JoinDTO request) {
        if (request.name() == null)
            throw new MemberException(MemberErrorCode.NICKNAME_NOT_EXIST);
        if (memberRepository.existsByPhoneNumber(request.phoneNumber()))
            throw new MemberException(MemberErrorCode.DUPLICATE_PHONE_NUMBER);
        if (memberRepository.existsByEmail(request.email()))
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
    }

}
