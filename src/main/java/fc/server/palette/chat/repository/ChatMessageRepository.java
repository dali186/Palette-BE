package fc.server.palette.chat.repository;

import fc.server.palette.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    @Query("{roomId: ?0}")
    List<ChatMessage> findByRoomId(String Id);

    @Query(value = "{sender: ?0, roomId: ?1, createdAt: { $gt: ?2 } }", count = true)
    Long countRecentMessages(Long memberId, String roomId, LocalDateTime exitTime);

    Optional<ChatMessage> findTopByRoomIdOrderByCreatedAtDesc(String id);
}
