package fc.server.palette.chat.repository;

import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.entity.type.ChatRoomType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query(value = "{memberList: ?0, type: {$in: ['PERSONAL', 'SECONDHAND']}}")
    List<ChatRoom> findPersonalRoomByMemberId(Long memberId);

    @Query(value = "{memberList: ?0, type: {$in: ['MEETING', 'PURCHASE']}}")
    List<ChatRoom> findGroupRoomByMemberId(Long memberId);

    @Query(value = "{_id: ?0}")
    Optional<ChatRoom> findChatRoomById(String roomId);

    @Query(value = "{ 'enterList.?0': { $exists: true }, 'enterList.?1': { $exists: true }}")
    Optional<ChatRoom> findChatRoomByEnterList(Long memberId, Long opId);

    @Query(value = "{enterList: {$in: [?0, ?1]}, type: ?2}")
    Optional<ChatRoom> findChatRoomByEnterListAndType(Long memberId, Long opId, ChatRoomType type);

    @Query(value = "{memberList: {$in: [?0, ?1]}}")
    Optional<ChatRoom> findChatRoomByMemberList(Long memberId, Long opId);

    @Query(value = "{ 'enterList.?0': { $exists: true }, 'enterList.?1': { $exists: true }, type: 'PERSONAL'}")
    Optional<ChatRoom> findPersonalChatRoomByEnterList(Long memberId, Long opId);

    @Query(value = "{ 'enterList.?0': { $exists: true }, 'enterList.?1': { $exists: true }, contentId: ?2, type: 'SECONDHAND'}")
    Optional<ChatRoom> findSecondHandChatRoomByEnterList(Long memberId, Long opId, Long contentId);

    @Query(value = "{_id: ?0, memberList: ?1}")
    Optional<ChatRoom> findChatRoomByIdAndMemberId(String roomId, Long memberId);

    @Query(value = "{contentId: ?0, type: ?1}")
    Optional<ChatRoom> findChatRoomByContentIdAndType(Long contentId, ChatRoomType type);

}