package fc.server.palette.chat.repository;

import fc.server.palette.chat.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query(value = "{memberList: ?0, type: {$in: ['PERSONAL', 'SECONDHAND']}}")
    List<ChatRoom> findPersonalRoomByMemberId(Long memberId);

    @Query(value = "{memberList: ?0, type: {$in: ['MEETING', 'PURCHASE']}}")
    List<ChatRoom> findGroupRoomByMemberId(Long memberId);

    @Query(value = "{_id: ?0}")
    ChatRoom findChatRoomById(String roomId);

}
