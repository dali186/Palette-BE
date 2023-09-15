package fc.server.palette.meeting.service;

import fc.server.palette.meeting.dto.request.MeetingCreateRequestDto;
import fc.server.palette.meeting.dto.response.MeetingDetailResponseDto;
import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.dto.response.MeetingMemberResponseDto;
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

    @Transactional
    public List<MeetingListResponseDto> getMeetingList(Boolean isClose){
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingListResponseDto> meetingResponseDtoList = new ArrayList<>();
        if(isClose) {
            for (Meeting meeting : meetings) {
                if (!meeting.isClosing()) {
                    MeetingListResponseDto meetingResponseDto = MeetingListResponseDto.builder()
                            .id(meeting.getId())
                            .category(meeting.getCategory().getDescription())
                            .type(meeting.getType().getDescription())
                            .jobs(meeting.getJob().stream()
                                    .map(Job::getValue)
                                    .collect(Collectors.toList()))
                            .positions(meeting.getPosition().stream().map(Position::getValue).collect(Collectors.toList()))
                            .sex(meeting.getSex().getValue())
                            .ageRange(meeting.getAgeRange().stream().map(Age::getDescription).collect(Collectors.toList()))
                            .image(meeting.getImage())
                            .title(meeting.getTitle())
                            .description(meeting.getDescription())
                            .headCount(meeting.getHeadCount())
                            .recruitedPersonnel(meeting.getRecruitedPersonnel())
                            .startDate(meeting.getStartDate())
                            .endDate(meeting.getEndDate())
                            .onOff(meeting.isOnOff())
                            .place(meeting.getPlace())
                            .week(meeting.getWeek().getDescription())
                            .days(meeting.getDays().stream().map(Day::getDescription).collect(Collectors.toList()))
                            .time(meeting.getTime())
                            .progressTime(meeting.getProgressTime())
                            .isClosing(meeting.isClosing())
                            .hits(meeting.getHits())
                            .likes(meeting.getLikes())
                            .createdAt(meeting.getCreatedAt())
                            .build();
                    meetingResponseDtoList.add(meetingResponseDto);
                }
            }
        }
        else {
            for (Meeting meeting : meetings){
                MeetingListResponseDto meetingResponseDto = MeetingListResponseDto.builder()
                        .id(meeting.getId())
                        .category(meeting.getCategory().getDescription())
                        .type(meeting.getType().getDescription())
                        .jobs(meeting.getJob().stream()
                                .map(Job::getValue)
                                .collect(Collectors.toList()))
                        .positions(meeting.getPosition().stream().map(Position::getValue).collect(Collectors.toList()))
                        .sex(meeting.getSex().getValue())
                        .ageRange(meeting.getAgeRange().stream().map(Age::getDescription).collect(Collectors.toList()))
                        .image(meeting.getImage())
                        .title(meeting.getTitle())
                        .description(meeting.getDescription())
                        .headCount(meeting.getHeadCount())
                        .recruitedPersonnel(meeting.getRecruitedPersonnel())
                        .startDate(meeting.getStartDate())
                        .endDate(meeting.getEndDate())
                        .onOff(meeting.isOnOff())
                        .place(meeting.getPlace())
                        .week(meeting.getWeek().getDescription())
                        .days(meeting.getDays().stream().map(Day::getDescription).collect(Collectors.toList()))
                        .time(meeting.getTime())
                        .progressTime(meeting.getProgressTime())
                        .isClosing(meeting.isClosing())
                        .hits(meeting.getHits())
                        .likes(meeting.getLikes())
                        .createdAt(meeting.getCreatedAt())
                        .build();
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
        System.out.println(meetings);

        if(!onOff.isEmpty()){
            boolean isOnline = "온라인".equalsIgnoreCase(onOff);
            meetings = meetings.stream()
                    .filter(meeting -> meeting.isOnOff() == isOnline)
                    .collect(Collectors.toList());
        }
        System.out.println(meetings);
        meetings = meetings.stream()
                .filter(meeting -> (type.isEmpty() || type.equals(meeting.getType().getDescription())) &&
                        (position.isEmpty() || meeting.getPosition().stream()
                                .map(Position::getValue).anyMatch(pos -> position.equals(pos))) &&
                        (sex.isEmpty() || sex.equals(meeting.getSex().getValue())) &&
                        (job.isEmpty() || job.stream().anyMatch(job1 -> meeting.getJob().stream()
                                .map(Job::getValue).anyMatch(job1::equals))))
                        .collect(Collectors.toList());
        System.out.println(meetings);
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
            MeetingListResponseDto meetingListResponseDto = MeetingListResponseDto.builder()
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
                    .image(meeting.getImage())
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
            return meetingListResponseDto;
        }).collect(Collectors.toList());
        return meetingListResponseDtoList;
    }

    @Transactional
    public MeetingDetailResponseDto createMeeting(MeetingCreateRequestDto meetingCreateRequestDto, Long loginUserId) {
        Member member = memberRepository.findById(loginUserId)
                .orElseThrow(()-> new RuntimeException("사용자를 찾을수없습니다."));

        Meeting meeting = Meeting.builder()
                .member(member)
                .category(Category.fromValue(meetingCreateRequestDto.getCategory()))
                .type(Type.fromValue(meetingCreateRequestDto.getType()))
                .job(Job.fromValue(meetingCreateRequestDto.getJobs()))
                .position(Position.fromValue(meetingCreateRequestDto.getPositions()))
                .sex(Sex.fromValue(meetingCreateRequestDto.getSex()))
                .ageRange(Age.fromValue(meetingCreateRequestDto.getAgeRange()))
               // .image(convertToMediaList(meetingCreateRequestDto.getImage()))
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
        MeetingMemberResponseDto responseMember = new MeetingMemberResponseDto(saveMeeting.getMember());
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
                .image(saveMeeting.getImage())
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
                .build();

    }

}
