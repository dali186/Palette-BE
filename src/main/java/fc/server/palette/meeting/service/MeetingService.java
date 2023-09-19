package fc.server.palette.meeting.service;

import fc.server.palette.meeting.dto.request.MeetingCreateRequestDto;
import fc.server.palette.meeting.dto.request.MeetingUpdateRequestDto;
import fc.server.palette.meeting.dto.response.MeetingDetailResponseDto;
import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.dto.response.MeetingMemberResponseDto;
import fc.server.palette.meeting.entity.Bookmark;
import fc.server.palette.meeting.entity.Media;
import fc.server.palette.meeting.entity.Meeting;
import fc.server.palette.meeting.entity.type.*;
import fc.server.palette.member.MemberRepository;
import fc.server.palette.member.entity.Member;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import fc.server.palette.member.entity.type.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final MediaRepository mediaRepository;
    private final MeetingMediaService meetingMediaService;

    public List<MeetingListResponseDto> getMeetingList(Boolean isClose){
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingListResponseDto> meetingResponseDtoList = new ArrayList<>();
        if(isClose) {
            for (Meeting meeting : meetings) {
                if (!meeting.isClosing()) {
                    MeetingListResponseDto meetingResponseDto = meetingListResponseDtoBuilder(meeting);
                    meetingResponseDtoList.add(meetingResponseDto);
                }
            }
        }
        else {
            for (Meeting meeting : meetings){
                MeetingListResponseDto meetingResponseDto = meetingListResponseDtoBuilder(meeting);
                meetingResponseDtoList.add(meetingResponseDto);
            }
        }
        return meetingResponseDtoList;
    }

    public List<MeetingListResponseDto> getMeetingFilterList(
            Boolean isClose, String filter, String onOff, String type,
            List<String> job, String position, String sex) {
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingListResponseDto> meetingListResponseDtoList = new ArrayList<>();

        if(isClose){
            meetings = meetings.stream().filter(meeting -> !meeting.isClosing()).collect(Collectors.toList());
        }

        if(!onOff.isEmpty()){
            boolean isOnline = "온라인".equalsIgnoreCase(onOff);
            meetings = meetings.stream()
                    .filter(meeting -> meeting.isOnOff() == isOnline)
                    .collect(Collectors.toList());
        }

        meetings = meetings.stream()
                .filter(meeting -> (type.isEmpty() || type.equals(meeting.getType().getDescription())) &&
                        (position.isEmpty() || meeting.getPosition().stream()
                                .map(Position::getValue).anyMatch(pos -> position.equals(pos))) &&
                        (sex.isEmpty() || sex.equals(meeting.getSex().getValue())) &&
                        (job.isEmpty() || job.stream().anyMatch(job1 -> meeting.getJob().stream()
                                .map(Job::getValue).anyMatch(job1::equals))))
                        .collect(Collectors.toList());

        switch (filter){
            case "최신순":
                meetings.sort(Comparator.comparing(Meeting::getCreatedAt).reversed());
                break;
            case "찜 많은 순":
                meetings.sort(Comparator.comparing(Meeting::getLikes).reversed());
                break;
            case "조회순":
                meetings.sort(Comparator.comparing(Meeting::getHits).reversed());
                break;
            case "모임시작일 순":
                meetings.sort(Comparator.comparing(Meeting::getStartDate));
                break;
            default:
                meetings.sort(Comparator.comparing(Meeting::getCreatedAt).reversed());
        }

        meetingListResponseDtoList = meetings.stream().map(meeting -> {
            MeetingListResponseDto meetingListResponseDto = meetingListResponseDtoBuilder(meeting);
            return meetingListResponseDto;
        }).collect(Collectors.toList());
        return meetingListResponseDtoList;
    }

    public MeetingDetailResponseDto createMeeting(MeetingCreateRequestDto meetingCreateRequestDto, Long loginUserId, List<MultipartFile> imges) {
        Member member = memberRepository.findById(loginUserId)
                .orElseThrow(()-> new RuntimeException("사용자를 찾을수없습니다."));

        List<Media> mediaList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();

        if (imges != null){
            for(MultipartFile image : imges){
                String imageUrl = meetingMediaService.uploadImage(image);
                urlList.add(imageUrl);
                Media media = Media.builder()
                        .url(imageUrl)
                        .build();
                mediaList.add(media);
            }
        }

        Meeting meeting = Meeting.builder()
                .member(member)
                .category(Category.fromValue(meetingCreateRequestDto.getCategory()))
                .type(Type.fromValue(meetingCreateRequestDto.getType()))
                .job(Job.fromValue(meetingCreateRequestDto.getJobs()))
                .position(Position.fromValue(meetingCreateRequestDto.getPositions()))
                .sex(Sex.fromValue(meetingCreateRequestDto.getSex()))
                .ageRange(Age.fromValue(meetingCreateRequestDto.getAgeRange()))
                .image(mediaList)
                .title(meetingCreateRequestDto.getTitle())
                .description(meetingCreateRequestDto.getDescription())
                .headCount(meetingCreateRequestDto.getHeadCount())
                .startDate(meetingCreateRequestDto.getStartDate())
                .endDate(meetingCreateRequestDto.getEndDate())
                .onOff(meetingCreateRequestDto.isOnOff())
                .place(meetingCreateRequestDto.getPlace())
                .week(Week.fromValue(meetingCreateRequestDto.getWeek()))
                .days(Day.fromValue(meetingCreateRequestDto.getDays()))
                .time(meetingCreateRequestDto.getTime())
                .progressTime(meetingCreateRequestDto.getProgressTime())
                .acceptType(AcceptType.fromValue(meetingCreateRequestDto.getAcceptType()))
                .build();

        Meeting saveMeeting = meetingRepository.save(meeting);

        for(String url : urlList){
            Media media = Media.builder()
                    .url(url)
                    .meeting(saveMeeting)
                    .build();
            mediaRepository.save(media);
        }

        MeetingMemberResponseDto responseMember = MeetingMemberResponseDto.builder()
                .nickname(saveMeeting.getMember().getNickname())
                .bio(saveMeeting.getMember().getBio())
                .image(saveMeeting.getMember().getImage())
                .build();

        return MeetingDetailResponseDto.builder()
                .meetingMemberResponseDto(responseMember)
                .id(saveMeeting.getId())
                .category(saveMeeting.getCategory().getDescription())
                .type(saveMeeting.getType().getDescription())
                .jobs(saveMeeting.getJob().stream()
                        .map(Job::getValue)
                        .collect(Collectors.toList()))
                .positions(saveMeeting.getPosition().stream()
                        .map(Position::getValue)
                        .collect(Collectors.toList()))
                .sex(saveMeeting.getSex().getValue())
                .ageRange(saveMeeting.getAgeRange().stream()
                        .map(Age::getDescription)
                        .collect(Collectors.toList()))
                .image(saveMeeting.getImage().stream()
                        .map(Media::getUrl)
                        .collect(Collectors.toList()))
                .title(saveMeeting.getTitle())
                .description(saveMeeting.getDescription())
                .headCount(saveMeeting.getHeadCount())
                .startDate(saveMeeting.getStartDate())
                .endDate(saveMeeting.getEndDate())
                .onOff(saveMeeting.isOnOff())
                .place(saveMeeting.getPlace())
                .week(saveMeeting.getWeek().getDescription())
                .days(saveMeeting.getDays().stream()
                        .map(Day::getDescription)
                        .collect(Collectors.toList()))
                .time(saveMeeting.getTime())
                .progressTime(saveMeeting.getProgressTime())
                .acceptType(saveMeeting.getAcceptType().getDescription())
                .hits(saveMeeting.getHits())
                .likes(saveMeeting.getLikes())
                .createdAt(saveMeeting.getCreatedAt())
                .build();

    }

    public void delete(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없습니다"));
        meetingRepository.deleteById(meetingId);
    }

    public void updateMeeting(Long meetingId, MeetingUpdateRequestDto meetingUpdateRequestDto) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        meeting.update(meetingUpdateRequestDto);
    }

    public MeetingDetailResponseDto getDetailMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        meeting.setHits(); //조회수 증가
        MeetingMemberResponseDto responseMember = MeetingMemberResponseDto.builder()
                .nickname(meeting.getMember().getNickname())
                .bio(meeting.getMember().getBio())
                .image(meeting.getMember().getImage())
                .build();
        return MeetingDetailResponseDto.builder()
                .meetingMemberResponseDto(responseMember)
                .id(meeting.getId())
                .category(meeting.getCategory().getDescription())
                .type(meeting.getType().getDescription())
                .jobs(meeting.getJob().stream()
                        .map(Job::getValue)
                        .collect(Collectors.toList()))
                .positions(meeting.getPosition().stream()
                        .map(Position::getValue)
                        .collect(Collectors.toList()))
                .sex(meeting.getSex().getValue())
                .ageRange(meeting.getAgeRange().stream()
                        .map(Age::getDescription)
                        .collect(Collectors.toList()))
                .image(meeting.getImage().stream()
                        .map(Media::getUrl)
                        .collect(Collectors.toList()))
                .title(meeting.getTitle())
                .description(meeting.getDescription())
                .headCount(meeting.getHeadCount())
                .startDate(meeting.getStartDate())
                .endDate(meeting.getEndDate())
                .onOff(meeting.isOnOff())
                .place(meeting.getPlace())
                .week(meeting.getWeek().getDescription())
                .days(meeting.getDays().stream()
                        .map(Day::getDescription)
                        .collect(Collectors.toList()))
                .time(meeting.getTime())
                .progressTime(meeting.getProgressTime())
                .acceptType(meeting.getAcceptType().getDescription())
                .hits(meeting.getHits())
                .likes(meeting.getLikes())
                .createdAt(meeting.getCreatedAt())
                .build();
    }

    public MeetingListResponseDto meetingListResponseDtoBuilder(Meeting meeting){
         return MeetingListResponseDto.builder()
                .id(meeting.getId())
                .category(meeting.getCategory().getDescription())
                .type(meeting.getType().getDescription())
                .jobs(meeting.getJob().stream()
                        .map(Job::getValue)
                        .collect(Collectors.toList()))
                .positions(meeting.getPosition().stream().
                        map(Position::getValue)
                        .collect(Collectors.toList()))
                .sex(meeting.getSex().getValue())
                .ageRange(meeting.getAgeRange().stream()
                        .map(Age::getDescription)
                        .collect(Collectors.toList()))
                .image(meeting.getImage().stream()
                        .map(Media::getUrl)
                        .collect(Collectors.toList()))
                .title(meeting.getTitle())
                .description(meeting.getDescription())
                .headCount(meeting.getHeadCount())
                .recruitedPersonnel(meeting.getRecruitedPersonnel())
                .startDate(meeting.getStartDate())
                .endDate(meeting.getEndDate())
                .onOff(meeting.isOnOff())
                .place(meeting.getPlace())
                .week(meeting.getWeek().getDescription())
                .days(meeting.getDays().stream()
                        .map(Day::getDescription)
                        .collect(Collectors.toList()))
                .time(meeting.getTime())
                .progressTime(meeting.getProgressTime())
                .isClosing(meeting.isClosing())
                .hits(meeting.getHits())
                .likes(meeting.getLikes())
                .createdAt(meeting.getCreatedAt())
                .build();
    }

    public void likesMeeting(Long meetingId, Long loginUser) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        Member member = memberRepository.findById(loginUser)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 아이디가 존재하지 않습니다."));
        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .meeting(meeting)
                .build();
        bookmarkRepository.save(bookmark);
        meeting.likes();
    }

    public void dislikesMeeting(Long meetingId, Long loginUser) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        Member member = memberRepository.findById(loginUser)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 아이디가 존재하지 않습니다."));

        Bookmark bookmark = bookmarkRepository.findByMemberAndMeeting(member, meeting);
        if(bookmark != null){
            bookmarkRepository.delete(bookmark);
        }
        meeting.dislike();
    }

    public void closeMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        if (meeting.isClosing()){
            throw new IllegalArgumentException("이미 모집된 마감입니다.");
        }
        meeting.clsed();
    }

    public void reopenMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        meeting.reopen();
    }

    public List<MeetingListResponseDto> recommendMeeting(Long meetingId) {
        Meeting baseMeeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        List<Meeting> recommededMeeting = new ArrayList<>();

        if(recommededMeeting.size() < 2){
            List<Meeting> sameOnOffMeeting = meetingRepository.findByOnOffAndIsClosingFalse(baseMeeting.isOnOff());
            sameOnOffMeeting.remove(baseMeeting);
            recommededMeeting.addAll(sameOnOffMeeting.subList(0, Math.min(2, sameOnOffMeeting.size())));
        }

        if (recommededMeeting.size() < 2){
            List<Meeting> sameTypeMeeting = meetingRepository.findByTypeAndIsClosingFalse(baseMeeting.getType());
            sameTypeMeeting.remove(baseMeeting);
            recommededMeeting.addAll(sameTypeMeeting.subList(0, Math.min(2 - recommededMeeting.size(), sameTypeMeeting.size())));
        }

        if (recommededMeeting.size() < 2){
            List<Job> jobs = baseMeeting.getJob();
            List<Meeting> sameJobsMeeting = meetingRepository.findByJobInAndIsClosingFalse(jobs);
            sameJobsMeeting.remove(baseMeeting);
            recommededMeeting.addAll(sameJobsMeeting.subList(0, Math.min(2 - recommededMeeting.size(), sameJobsMeeting.size())));
        }

        if (recommededMeeting.size() < 2) {
            List<Position> positions = baseMeeting.getPosition();
            List<Meeting> samePositionsMeetings = meetingRepository.findByPositionInAndIsClosingFalse(positions);
            samePositionsMeetings.remove(baseMeeting);
            recommededMeeting.addAll(samePositionsMeetings.subList(0, Math.min(2 - recommededMeeting.size(), samePositionsMeetings.size())));
        }

        if (recommededMeeting.size() < 2) {
            Sex sex = baseMeeting.getSex();
            List<Meeting> sameSexMeetings = meetingRepository.findBySexAndIsClosingFalse(sex);
            sameSexMeetings.remove(baseMeeting);
            recommededMeeting.addAll(sameSexMeetings.subList(0, Math.min(2 - recommededMeeting.size(), sameSexMeetings.size())));
        }

        if (recommededMeeting.size() < 2) {
            List<Meeting> mostLikedMeetings = meetingRepository.findTop2ByIsClosingFalseOrderByLikesDesc();
            recommededMeeting.addAll(mostLikedMeetings);
        }

        return recommededMeeting.stream()
                .map(this::meetingListResponseDtoBuilder)
                .collect(Collectors.toList());

    }
}
