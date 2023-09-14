package fc.server.palette.meeting.controller;

import fc.server.palette.meeting.dto.request.MeetingCreateRequestDto;
import fc.server.palette.meeting.dto.response.MeetingDetailResponseDto;
import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/develop")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @GetMapping("")
    public ResponseEntity<?> getMeetingList(@RequestParam Boolean isClose){
        List<MeetingListResponseDto> meetingResponseDtoList;
        meetingResponseDtoList = meetingService.getMeetingList(isClose);
        return ResponseEntity.ok(meetingResponseDtoList);
    }

//    @GetMapping("/filter")
//    public ResponseEntity<?> getMeetingFilterList(
//            @RequestParam Boolean isClose,
//            @RequestParam String filter,
//            @RequestParam List<String> onOff,
//            @RequestParam List<String> type,
//            @RequestParam List<String> job,
//            @RequestParam List<String> position){
//        List<MeetingListResponseDto> meetingListResponseDtoList = meetingService.getMeetingFilterList(isClose, filter, onOff, type, job, position);
//        return ResponseEntity.ok(meetingListResponseDtoList);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createMeeting(@RequestBody MeetingCreateRequestDto meetingCreateRequestDto){
        MeetingDetailResponseDto meetingDetailResponseDto = meetingService.createMeeting(meetingCreateRequestDto, 1L);
        return ResponseEntity.ok(meetingDetailResponseDto);
    }


}
