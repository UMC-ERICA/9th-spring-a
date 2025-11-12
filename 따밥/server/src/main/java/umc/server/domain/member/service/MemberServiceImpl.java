package umc.server.domain.member.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    public MemberServiceImpl(MemberRepository memberRepository, MemberMissionRepository memberMissionRepository, ReviewRepository reviewRepository) {

        this.memberRepository = memberRepository;
        this.memberMissionRepository = memberMissionRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Member> findByUsername(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        return member;
    }

    @Override
    public void join(MemberReqDTO.JoinDTO request) {
        validateRequest(request);

        Member member = Member.builder()
                .email(request.getEmail())
                .nickname(request.getName())
                .gender(request.getGender())
                .birth(request.getBirthday())
                .photo(request.getMemberPhoto())
                .phoneNumber(request.getPhoneNumber())
                .build();

        memberRepository.save(member);
    }

    @Override
    public MemberResDTO.HomeTopDTO getHomeTop(Long memberId, String region, Long cursor, int size) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

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
        Member member = memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageDTO(member);
    }

    @Override
    public MemberResDTO.MyReviewsDTO getMyReviews(Long memberId) {
        Member member = memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        List<Review> reviews = reviewRepository.findAllByMemberId(memberId);

        return MemberConverter.toMyReviewsDTO(member, reviews);
    }

    private void validateRequest(MemberReqDTO.JoinDTO request) {
        if (request.getName() == null)
            throw new MemberException(MemberErrorCode.NICKNAME_NOT_EXIST);
        if (memberRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new MemberException(MemberErrorCode.DUPLICATE_PHONE_NUMBER);
        if (memberRepository.existsByEmail(request.getEmail()))
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
    }

}
