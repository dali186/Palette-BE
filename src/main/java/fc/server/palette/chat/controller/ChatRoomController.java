package fc.server.palette.chat.controller;

import fc.server.palette.chat.dto.response.ChatRoomListResponseDto;
import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.service.ChatMessageService;
import fc.server.palette.chat.service.ChatRoomService;
import fc.server.palette.member.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    //채팅방 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getChatRoomList(@RequestParam("type") String type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ChatRoomListResponseDto> chatRoomList = new ArrayList<>();
        Long memberId = userDetails.getMember().getId();

        if (type.equals("p")) {
            setChatRoomListResponse(chatRoomList, chatRoomService.findPersonalChatRoomList(memberId), memberId);
        } else if (type.equals("g")) {
            setChatRoomListResponse(chatRoomList, chatRoomService.findGroupChatRoomList(memberId), memberId);
        }

        return ResponseEntity.ok(chatRoomList);
    }

    //Dto에 정보를 설정하는 메소드
    private List<ChatRoomListResponseDto> setChatRoomListResponse(List<ChatRoomListResponseDto> chatRoomResponseList, List<ChatRoom> chatRoomList, Long memberId) {
        for (ChatRoom rooms : chatRoomList) {
            ChatRoomListResponseDto response = new ChatRoomListResponseDto();
            response.setId(rooms.getId());
            response.setTitle(rooms.getTitle());
            response.setImage(rooms.getThumbnail());
            response.setMemberList(rooms.getMemberList());

            Optional<ChatMessage> message = chatMessageService.findRecentMessage(rooms.getId());
            if (message.isPresent()) {
                response.setRecentMessage(message.get().getContent());
                response.setRecentTime(message.get().getCreatedAt());
            }

            LocalDateTime exitedAt = rooms.getExitList().get(memberId);
            response.setUnRead(chatMessageService.countRecentMessages(memberId, rooms.getId(), exitedAt));
            chatRoomResponseList.add(response);
        }
        return chatRoomResponseList;
    }


}
