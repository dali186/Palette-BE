package fc.server.palette.chat.controller;

import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private ChatMessageRepository chatMessageRepository;
    private final SimpMessageSendingOperations template;

    @MessageMapping("/chat/send")
    @SendTo("/sub/public/{roomId}")
    public void sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String roomId) {
        chatMessageRepository.save(chatMessage);
        template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);
    }

    //    @MessageMapping("/chat.addUser")
    //    @SendTo("/topic/public")
    //    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    //
    //        return chatMessage;
    //    }
}
