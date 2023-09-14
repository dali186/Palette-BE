package fc.server.palette.meeting.service;

import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.entity.Meeting;
import fc.server.palette.meeting.entity.type.Age;
import fc.server.palette.meeting.entity.type.Day;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;

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

//    @Transactional
//    public MeetingResponse.MeetingResponseDto createMeeting(
//            Long loginUserId,
//            MeetingRequest.MeetingRequestDto meetingRequestDto,
//            List<MultipartFile> image
//    ){
//        Member member =
//    }
}
