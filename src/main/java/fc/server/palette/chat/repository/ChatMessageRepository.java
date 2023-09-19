package fc.server.palette.chat.repository;

import fc.server.palette.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    @Query("{roomId: ?0}")
    List<ChatMessage> findByRoomId(String Id);
}
