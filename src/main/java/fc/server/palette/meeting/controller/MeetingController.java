package fc.server.palette.meeting.controller;

import fc.server.palette.meeting.dto.request.MeetingCreateRequestDto;
import fc.server.palette.meeting.dto.request.MeetingUpdateRequestDto;
import fc.server.palette.meeting.dto.response.MeetingDetailResponseDto;
import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/filter")
    public ResponseEntity<?> getMeetingFilterList(
            @RequestParam Boolean isClose,
            @RequestParam String filter,
            @RequestParam String onOff,
            @RequestParam String type,
            @RequestParam List<String> job,
            @RequestParam String position,
            @RequestParam String sex
    ){
        List<MeetingListResponseDto> meetingListResponseDtoList = meetingService.getMeetingFilterList(isClose, filter, onOff, type, job, position, sex);
        return ResponseEntity.ok(meetingListResponseDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMeeting(@RequestBody MeetingCreateRequestDto meetingCreateRequestDto){
        MeetingDetailResponseDto meetingDetailResponseDto = meetingService.createMeeting(meetingCreateRequestDto, 1L);
        return ResponseEntity.ok(meetingDetailResponseDto);
    }


    @PostMapping("/update/{meetingId}")
    public ResponseEntity<?> updateMeeting(
            @PathVariable Long meetingId,
            @RequestBody MeetingUpdateRequestDto meetingUpdateRequestDto
    ){
        meetingService.updateMeeting(meetingId, meetingUpdateRequestDto);
        return ResponseEntity.ok("업데이트 완료");

    }

    @PostMapping("/delete/{meetingId}")
    public ResponseEntity<?> deleteMeeting(@PathVariable Long meetingId){
        meetingService.delete(meetingId);
        return ResponseEntity.ok("삭제완료");
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<?> detailMeeting(@PathVariable Long meetingId){
        return ResponseEntity.ok(meetingService.getDetailMeeting(meetingId));
    }

    @PostMapping("/like/{meetingId}")
    public ResponseEntity<?> likesMeeting(@PathVariable Long meetingId){
        meetingService.likesMeeting(meetingId, 1L);
        return ResponseEntity.ok("좋아요를 눌렀습니다.");
    }



}
