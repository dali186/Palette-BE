package fc.server.palette.chat.service;

import fc.server.palette.chat.dto.response.ChatRoomListResponseDto;
import fc.server.palette.chat.dto.response.ChatRoomNoticeListResponseDto;
import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    //채팅 저장
    @Transactional
    public void saveChat(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    @Transactional
    public Optional<ChatMessage> findChatMessageById(String msgId) {
        return chatMessageRepository.findById(msgId);
    }

    @Transactional
    public Optional<ChatMessage> findRecentMessage(String roomId) {
        return chatMessageRepository.findTopByRoomIdOrderByCreatedAtDesc(roomId);
    }

    @Transactional
    public Long countRecentMessages(Long memberId, String roomId, LocalDateTime time) {
        return chatMessageRepository.countRecentMessages(memberId, roomId, time);
    }

    //ChatRoomListDto에 정보를 설정하는 메소드
    @Transactional
    public List<ChatRoomListResponseDto> setChatRoomListResponse(List<ChatRoom> chatRoomList, Long memberId) {
        List<ChatRoomListResponseDto> chatRoomResponseList = new ArrayList<>();
        for (ChatRoom rooms : chatRoomList) {
            LocalDateTime exitedAt = rooms.getExitList().get(memberId);
            ChatRoomListResponseDto response = ChatRoomListResponseDto.builder()
                    .id(rooms.getId())
                    .title(rooms.getTitle())
                    .image(rooms.getThumbnail())
                    .members(rooms.getMemberList().size())
                    .unRead(countRecentMessages(memberId, rooms.getId(), exitedAt))
                    .build();
            Optional<ChatMessage> message = findRecentMessage(rooms.getId());
            if (message.isPresent()) {
                response.setRecentMessage(message.get().getContent());
                response.setRecentTime(message.get().getCreatedAt());
            }
            chatRoomResponseList.add(response);
        }
        return chatRoomResponseList;
    }

    //ChatRoomNoticeListDto에 정보를 설정하는 메소드
    @Transactional
    public List<ChatRoomNoticeListResponseDto> setChatRoomNoticeListResponse(ChatRoom chatRoom) {
        List<ChatRoomNoticeListResponseDto> chatRoomNoticeResponseList = new ArrayList<>();
        for (String noticeId : chatRoom.getNoticeList()) {
            ChatRoomNoticeListResponseDto response = ChatRoomNoticeListResponseDto
                    .builder()
                    .host(chatRoom.getHost())
                    .build();
            Optional<ChatMessage> message = findChatMessageById(noticeId);
            if (message.isPresent()) {
                response.setNoticeId(message.get().getId());
                response.setNotice(message.get().getContent());
                response.setMemberId(message.get().getSender());
                response.setCreatedAt(message.get().getCreatedAt());
            }
            chatRoomNoticeResponseList.add(response);
        }
        return chatRoomNoticeResponseList;
    }

}
