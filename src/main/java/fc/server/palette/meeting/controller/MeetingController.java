package fc.server.palette.meeting.controller;

import fc.server.palette.meeting.dto.request.MeetingCreateRequestDto;
import fc.server.palette.meeting.dto.request.MeetingUpdateRequestDto;
import fc.server.palette.meeting.dto.response.MeetingDetailResponseDto;
import fc.server.palette.meeting.dto.response.MeetingListResponseDto;
import fc.server.palette.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> createMeeting(
            @RequestPart(value = "dto") MeetingCreateRequestDto meetingCreateRequestDto,
            @RequestPart(value = "file", required = false)List<MultipartFile> images
    ){
        MeetingDetailResponseDto meetingDetailResponseDto = meetingService.createMeeting(meetingCreateRequestDto, 1L, images);
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

    @PostMapping("/like/cancel/{meetingId}")
    public ResponseEntity<?> dislikesMeeting(@PathVariable Long meetingId){
        meetingService.dislikesMeeting(meetingId, 1L);
        return ResponseEntity.ok("좋아요를 취소했습니다.");
    }

    @PostMapping("/close/{meetingId}")
    public ResponseEntity<?> closeMeeting(@PathVariable Long meetingId){
        meetingService.closeMeeting(meetingId);
        return ResponseEntity.ok("모집 마감되었습니다.");
    }

    @PostMapping("/reopen/{meetingId}")
    public ResponseEntity<?> reopenMeeting(@PathVariable Long meetingId){
        meetingService.reopenMeeting(meetingId);
        return ResponseEntity.ok("모집이 시작되었습니다.");
    }

    @GetMapping("/recommend/{meetingId}")
    public ResponseEntity<?> recommendMeeting(@PathVariable Long meetingId){
        return ResponseEntity.ok(meetingService.recommendMeeting(meetingId));
    }

    @PostMapping("/participate/check/{meetingId}")
    public ResponseEntity<?> checkParticipate(@PathVariable Long meetingId){
        if(!meetingService.checkParticipateMeeting(meetingId, 1L)){
            return ResponseEntity.ok("가입요건이 맞지 않아요");
        }
        else {
            return ResponseEntity.ok("가입요건이 충족되었습니다.");
        }
    }


}
