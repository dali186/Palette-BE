package fc.server.palette.chat.service;

import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public Optional<ChatMessage> findRecentMessage(String roomId) {
        return chatMessageRepository.findTopByRoomIdOrderByCreatedAtDesc(roomId);
    }


    @Transactional
    public Long countRecentMessages(Long memberId, String roomId, LocalDateTime time) {
        return chatMessageRepository.countRecentMessages(memberId, roomId, time);
    }
}
