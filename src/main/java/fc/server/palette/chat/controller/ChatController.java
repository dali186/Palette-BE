package fc.server.palette.chat.controller;

import fc.server.palette.chat.dto.request.ChatRoomNoticeDto;
import fc.server.palette.chat.dto.request.ChatRoomOpenDto;
import fc.server.palette.chat.dto.response.ChatRoomDetailDto;
import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.entity.type.ChatMessageType;
import fc.server.palette.chat.entity.type.ChatRoomType;
import fc.server.palette.chat.service.ChatRoomService;
import fc.server.palette.chat.service.ChatService;
import fc.server.palette.meeting.service.MeetingService;
import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.member.entity.Member;
import fc.server.palette.purchase.service.PurchaseService;
import fc.server.palette.secondhand.service.SecondhandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatMessageService;
    private final MeetingService meetingService;
    private final PurchaseService purchaseService;
    private final SecondhandService secondhandService;
    private final SimpMessageSendingOperations template;

    //채팅방 생성

    /**
     * 채팅방 유입 경로
     * <p>
     * - 개인 채팅
     * (생성, 입장 - 호스트/참여자) 마이페이지 -> 채팅하기, title memberId_otherId
     * (생성 - 참여자) 중고거래 -> 중고거래 시작하기,
     * <p>
     * <p>
     * - 단체 채팅
     * (생성 - 호스트) 같이 성장해요 -> 채팅방 개설하기
     * (입장 - 참여자) 같이 성장해요 -> 채팅하기
     * (생성) 같이사요 -> 게시글 등록 시
     * (입장) 같이사요 -> 공동구매 제안하기
     */
    //개인 채팅방 생성
    //TODO 개인 채팅방은 api이용해서 생성, 단체 채팅방은 해당 repository에서 생성
    @PostMapping("/open")
    public ResponseEntity<?> personalChatRoomOpen(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ChatRoomOpenDto request) {
        Long memberId = userDetails.getMember().getId();

        return ResponseEntity.ok(chatRoomService.openPersonalChatRoom(memberId, request));
    }

    //채팅방 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> chatRoomList(@RequestParam("type") String type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getMember().getId();
        if (type.equals("p")) {
            return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findPersonalChatRoomList(memberId), memberId));
        }

        return ResponseEntity.ok(chatMessageService.setChatRoomListResponse(chatRoomService.findGroupChatRoomList(memberId), memberId));
    }

    //채팅방 관련 정보 조회
    //TODO PURCHASE의 경우 계좌번호
    @GetMapping("/room/detail")
    public ResponseEntity<?> roomDetails(@RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
        ChatRoomType type = chatRoom.getType();
        Long contentId = chatRoom.getContentId();
        ChatRoomDetailDto response = new ChatRoomDetailDto();
        response.setHost(chatRoom.getHost());
        if (!chatRoom.getNoticeList().isEmpty()) {
            String noticeId = chatRoom.getNoticeList().get(chatRoom.getNoticeList().size() - 1);
            response.setNotice(chatMessageService.findChatMessageById(noticeId).get().getContent());
        }
        if (type.equals(ChatRoomType.MEETING)) {
            response.setContentNotice(meetingService.getDetailMeeting(contentId).toChatRoomInfo());
        } else if (type.equals(ChatRoomType.PURCHASE)) {
            response.setContentNotice(purchaseService.getOffer(contentId).toChatRoomInfo());
        } else if (type.equals(ChatRoomType.SECONDHAND)) {
            response.setContentNotice(secondhandService.getProduct(contentId).toChatRoomInfo());
        }
        return ResponseEntity.ok(response);
    }

    //공지 등록
    @PostMapping("/notice")
    public ResponseEntity<?> noticeSave(@RequestBody ChatRoomNoticeDto request) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(request.getRoomId());
        if (!chatRoom.getNoticeList().contains(request.getMessageId())) {
            chatRoom.getNoticeList().add(request.getMessageId());
            chatRoomService.updateChatRoom(chatRoom);
        }

        return ResponseEntity.ok(request);
    }

    //공지 리스트 조회
    //TODO memberimageUrl 추가 로직
    @GetMapping("/notice")
    public ResponseEntity<?> noticeList(@RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);

        return ResponseEntity.ok(chatMessageService.setChatRoomNoticeListResponse(chatRoom));
    }

    //채팅방 나가기
    //TODO userId가 아닌 nickname 받아서 수정
    @DeleteMapping("/exit")
    public ResponseEntity<?> memberRemove(@RequestParam("roomId") String roomId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
        Member member = userDetails.getMember();
        if (member.getId().equals(chatRoom.getHost()) && !chatRoom.getType().equals(ChatRoomType.PERSONAL)) {
            throw new IllegalArgumentException("호스트는 나갈 수 없습니다.");
        }
        chatRoom.getMemberList().remove(member.getId());
        chatRoom.getExitList().remove(member.getId());

        if (chatRoom.getMemberList().size() == 0) {
            chatRoomService.deleteChatRoom(chatRoom);
        }

        if (chatRoom.getType().equals(ChatRoomType.MEETING) || chatRoom.getType().equals(ChatRoomType.PURCHASE)) {
            ChatMessage chatMessage = ChatMessage.builder()
                    .sender(member.getId())
                    .type(ChatMessageType.LEAVE)
                    .content(member.getNickname() + "님이 나갔습니다.")
                    .roomId(chatRoom.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
            chatMessageService.saveChat(chatMessage);
            template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);
        }

        chatRoomService.updateChatRoom(chatRoom);

        return ResponseEntity.ok("삭제되었습니다");
    }

    /**
     * related to Socket
     */

    @MessageMapping("/chat/send")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setCreatedAt(LocalDateTime.now());
        chatMessageService.saveChat(chatMessage);
        template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat/enter")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @AuthenticationPrincipal CustomUserDetails userDetails) {
        headerAccessor.getSessionAttributes().put("memberId", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomId", chatMessage.getRoomId());

        return chatMessage;
    }
}
