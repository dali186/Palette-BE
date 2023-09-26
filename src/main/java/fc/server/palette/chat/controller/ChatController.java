package fc.server.palette.chat.controller;

import fc.server.palette.chat.dto.request.ChatRoomNoticeRequestDto;
import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.entity.type.ChatMessageType;
import fc.server.palette.chat.entity.type.ChatRoomType;
import fc.server.palette.chat.service.ChatRoomService;
import fc.server.palette.chat.service.ChatService;
import fc.server.palette.member.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatMessageService;
    private final SimpMessageSendingOperations template;

    //채팅방 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> chatRoomList(@RequestParam("type") String type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMember().getId();

        if (type.equals("p")) {
            return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findPersonalChatRoomList(memberId), memberId));
        }

        return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findGroupChatRoomList(memberId), memberId));
    }

//    //채팅방 관련 정보 조회
//    @GetMapping("/room/detail")
//    public ResponseEntity<?> roomDetails(@RequestParam("roomId") String roomId) {
//        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
//        ChatRoomType type = chatRoom.getType();
//
//    }

    //공지 등록
    @PostMapping("/notice")
    public ResponseEntity<?> noticeSave(@RequestBody ChatRoomNoticeRequestDto request) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(request.getRoomId());
        chatRoom.getNoticeList().add(request.getMessageId());
        chatRoomService.updateChatRoom(chatRoom);

        return ResponseEntity.ok(request);
    }

    //공지 리스트 조회
    //TODO memberimageUrl 추가 로직
    @GetMapping("/notice")
    public ResponseEntity<?> noticeList(@RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);

        return ResponseEntity.ok(chatMessageService.setChatRoomNoticeListResponse(chatRoom));
    }

    //채팅방 나가기
    //TODO userId가 아닌 nickname 받아서 수정
    @DeleteMapping("/exit")
    public ResponseEntity<?> memberRemove(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
        Long memberId = userDetails.getMember().getId();
        chatRoom.getMemberList().remove(memberId);
        chatRoom.getExitList().remove(memberId);

        if (chatRoom.getType().equals(ChatRoomType.MEETING) || chatRoom.getType().equals(ChatRoomType.PURCHASE)) {
            ChatMessage chatMessage = ChatMessage.builder()
                    .sender(memberId)
                    .type(ChatMessageType.SYSTEM)
                    .content(memberId + "님이 나갔습니다.")
                    .roomId(chatRoom.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
            chatMessageService.saveChat(chatMessage);
            template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);
        }

        chatRoomService.updateChatRoom(chatRoom);

        return ResponseEntity.ok("삭제되었습니다");
    }

    /**
     * related to Socket
     */

    @MessageMapping("/chat/send")
    @SendTo("/sub/public/{roomId}")
    public void sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String roomId) {
        chatMessageService.saveChat(chatMessage);
        template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/chat/enter")
    @SendTo("/sub/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomId", chatMessage.getRoomId());

        return chatMessage;
    }
}
