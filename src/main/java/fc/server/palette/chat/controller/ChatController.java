package fc.server.palette.chat.controller;

import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.service.ChatService;
import fc.server.palette.chat.service.ChatRoomService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatMessageService;
    private final SimpMessageSendingOperations template;

    //채팅방 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getChatRoomList(@RequestParam("type") String type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMember().getId();

        if (type.equals("p")) {
            return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findPersonalChatRoomList(memberId), memberId));
        }

        return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findGroupChatRoomList(memberId), memberId));
    }

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
