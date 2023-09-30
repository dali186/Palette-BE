package fc.server.palette.chat.service;

import fc.server.palette.chat.dto.request.ChatRoomOpenDto;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.entity.type.ChatRoomType;
import fc.server.palette.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

//    @Transactional
//    public String openGroupChatRoom(Long memberId, ChatRoomOpenDto request) {
//        ChatRoom chatRoom = request.toEntity();
//        chatRoom.setHost(memberId);
//        chatRoom.setContentId(request.getContentId());
//        chatRoom.getMemberList().add(memberId);
//        chatRoom.getExitList().put(memberId, LocalDateTime.now());
//        chatRoom.getEnterList().put(memberId, LocalDateTime.now());
//
//        return chatRoomRepository.save(chatRoom).getId();
//    }

    //현재 둘 다 있는 방 / 한명이 나간 방 / 중고거래 게시글마다
    @Transactional
    public String openPersonalChatRoom(Long memberId, ChatRoomOpenDto request) {
        Long opId = request.getParticipant();
        Optional<ChatRoom> duplicatedRoom = chatRoomRepository.findChatRoomByEnterList(memberId, opId);
        if (!duplicatedRoom.isPresent()) {
            return openNewChatRoom(memberId, request).getId();
        } else {
            if (duplicatedRoom.get().getMemberList().size() == 2) {
                return duplicatedRoom.get().getId();
            }
            if (duplicatedRoom.get().getType().equals(ChatRoomType.SECONDHAND) && !duplicatedRoom.get().getContentId().equals(request.getContentId())) {
                return openNewChatRoom(memberId, request).getId();
            }
            List<Long> memberList = duplicatedRoom.get().getMemberList();
            Map<Long, LocalDateTime> enterList = duplicatedRoom.get().getEnterList();
            Map<Long, LocalDateTime> exitList = duplicatedRoom.get().getExitList();
            if (!memberList.contains(memberId)) {
                memberList.add(memberId);
                enterList.put(memberId, LocalDateTime.now());
                exitList.put(memberId, LocalDateTime.now());

                chatRoomRepository.save(duplicatedRoom.get());
            }
            if (!memberList.contains(request.getParticipant())) {
                memberList.add(request.getParticipant());
                enterList.put(request.getParticipant(), LocalDateTime.now());
                exitList.put(request.getParticipant(), LocalDateTime.now());

                chatRoomRepository.save(duplicatedRoom.get());
            }
        }
        return duplicatedRoom.get().getId();
    }

    private ChatRoom openNewChatRoom(Long memberId, ChatRoomOpenDto request) {
        ChatRoom chatRoom = new ChatRoom();
        Long opId = request.getParticipant();

        chatRoom.setTitle(memberId + "_" + opId);
        chatRoom.setType(request.getType());
        chatRoom.getMemberList().add(opId);
        chatRoom.getMemberList().add(memberId);
        chatRoom.getEnterList().put(opId, LocalDateTime.now());
        chatRoom.getEnterList().put(memberId, LocalDateTime.now());
        chatRoom.getExitList().put(opId, LocalDateTime.now());
        chatRoom.getExitList().put(memberId, LocalDateTime.now());
        if (request.getType().equals(ChatRoomType.SECONDHAND)) {
            chatRoom.setContentId(request.getContentId());
        }
        return chatRoomRepository.save(chatRoom);
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
        return chatRoomRepository.findChatRoomById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방을 찾을 수 없습니다."));
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

    //단체 채팅방 유저 추가, meeting - confirm, purchase - 공동구매 제안
    @Transactional
    public void addParticipant() {

    }

    @Transactional
    public void deleteChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.delete(chatRoom);
    }
}