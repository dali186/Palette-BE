package fc.server.palette.chat.service;

import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public String openChatRoom(ChatRoom chatRoom) {
        ChatRoom savedRoom = chatRoomRepository.save(chatRoom);
        return savedRoom.getId();
    }

    @Transactional
    public List<ChatRoom> findPersonalChatRoomList(Long memberId) {
        return chatRoomRepository.findPersonalRoomByMemberId(memberId);
    }

    @Transactional
    public List<ChatRoom> findGroupChatRoomList(Long memberId) {
        return chatRoomRepository.findGroupRoomByMemberId(memberId);
    }

    @Transactional
    public void updateChatRoomExitTime(Long memberId, String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(roomId);
        chatRoom.getExitList().put(memberId, LocalDateTime.now());
        chatRoomRepository.save(chatRoom);
    }
}
