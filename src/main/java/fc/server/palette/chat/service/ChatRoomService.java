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

    //개인 톡방 조회
    @Transactional
    public List<ChatRoom> findPersonalChatRoomList(Long memberId) {
        return chatRoomRepository.findPersonalRoomByMemberId(memberId);
    }

    //단체 톡방 조회
    @Transactional
    public List<ChatRoom> findGroupChatRoomList(Long memberId) {
        return chatRoomRepository.findGroupRoomByMemberId(memberId);
    }

    //roomId로 채팅방 조회
    @Transactional
    public ChatRoom findChatRoomById(String roomId) {
        return chatRoomRepository.findChatRoomById(roomId);
    }

    //ChatRoom 정보 업데이트
    @Transactional
    public void updateChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    //소켓 연결 해제 시 시간 정보 업데이트
    @Transactional
    public void updateChatRoomExitTime(Long memberId, String roomId) {
        ChatRoom chatRoom = findChatRoomById(roomId);
        chatRoom.getExitList().put(memberId, LocalDateTime.now());
        chatRoomRepository.save(chatRoom);
    }
}
