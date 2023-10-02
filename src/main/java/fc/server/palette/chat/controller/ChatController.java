package fc.server.palette.chat.controller;

import fc.server.palette._common.s3.S3DirectoryNames;
import fc.server.palette._common.s3.S3ImageUploader;
import fc.server.palette.chat.dto.request.ChatMessageImageDto;
import fc.server.palette.chat.dto.request.ChatRoomNoticeDto;
import fc.server.palette.chat.dto.request.ChatRoomOpenDto;
import fc.server.palette.chat.dto.response.*;
import fc.server.palette.chat.entity.ChatMessage;
import fc.server.palette.chat.entity.ChatRoom;
import fc.server.palette.chat.entity.type.ChatMessageType;
import fc.server.palette.chat.entity.type.ChatRoomType;
import fc.server.palette.chat.service.ChatRoomService;
import fc.server.palette.chat.service.ChatService;
import fc.server.palette.meeting.service.MeetingService;
import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.member.entity.Member;
import fc.server.palette.member.repository.MemberRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatMessageService;
    private final MeetingService meetingService;
    private final PurchaseService purchaseService;
    private final SecondhandService secondhandService;
    private final MemberRepository memberRepository;

    private final S3ImageUploader s3ImageUploader;
    private final SimpMessageSendingOperations template;

    //개인 채팅방 생성
    @PostMapping("/open")
    public ResponseEntity<?> personalChatRoomOpen(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ChatRoomOpenDto request) {
        Long memberId = userDetails.getMember().getId();
        Map<String, String> response = new HashMap<>();
        response.put("roomId", chatRoomService.openPersonalChatRoom(memberId, request));

        return ResponseEntity.ok(response);
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
    public ResponseEntity<?> roomDetails(@RequestParam("roomId") String roomId, @AuthenticationPrincipal CustomUserDetails userDetails) {
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
            response.setContentNotice(meetingService.getDetailMeeting(userDetails.getMember().getId(), contentId).toChatRoomInfo());
            return ResponseEntity.ok(response);
        }
        if (type.equals(ChatRoomType.PURCHASE)) {
            response.setContentNotice(purchaseService.getOffer(contentId).toChatRoomInfo());
            return ResponseEntity.ok(response);
        }
        if (type.equals(ChatRoomType.SECONDHAND)) {
            response.setContentNotice(secondhandService.getProduct(contentId).toChatRoomInfo());
            return ResponseEntity.ok(response);
        }
        throw new IllegalArgumentException("해당 채팅방 정보가 없습니다.");
    }

    //채팅방 메세지 목록 조회
    @GetMapping("/history")
    public ResponseEntity<?> messageList(@RequestParam("roomId") String roomId) {
        List<ChatMemberDetailDto> memberList = chatRoomService.findChatRoomById(roomId).getEnterList().keySet()
                .stream()
                .map(id -> {
                    ChatMemberDetailDto memberDetail = new ChatMemberDetailDto();
                    Optional<Member> member = memberRepository.findById(id);
                    memberDetail.setId(id);
                    memberDetail.setNickName(member.get().getNickname());
                    memberDetail.setProfileUrl(member.get().getImage());
                    return memberDetail;
                })
                .collect(Collectors.toList());

        List<ChatMessagesDto> messageList = chatMessageService.findChatMessageByRoomId(roomId).stream()
                .map(msg -> {
                    ChatMemberDetailDto detail = memberList.stream()
                            .filter(member -> member.getId().equals(msg.getMemberId()))
                            .findFirst()
                            .orElse(new ChatMemberDetailDto());
                    msg.setProfileImgUrl(detail.getProfileUrl());
                    msg.setNickName(detail.getNickName());
                    return msg;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageList);
    }

    //채팅방 유저 목록 조회
    @GetMapping("/member")
    public ResponseEntity<?> chatRoomUserList(@RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
        List<Long> memberIdList = chatRoom.getMemberList();

        List<ChatRoomUserListDto> memberList = memberIdList.stream()
                .map(id -> {
                    Optional<Member> member = memberRepository.findById(id);
                    return ChatRoomUserListDto.builder()
                            .memberId(id)
                            .nickName(member.get().getNickname())
                            .profileImgUrl(member.get().getImage())
                            .host(chatRoom.getHost())
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(memberList);
    }

    //채팅 이미지 전송
    @PostMapping("/file")
    public ResponseEntity<?> imageFileSend(@RequestPart("detail") ChatMessageImageDto request,
                                           @RequestPart("file") List<MultipartFile> images,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<String> imgUrls = s3ImageUploader.save(S3DirectoryNames.CHAT, images);
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(userDetails.getMember().getId())
                .roomId(request.getRoomId())
                .image(imgUrls)
                .type(request.getType())
                .createdAt(LocalDateTime.now())
                .build();

        List<ChatMessageImageDetailDto> response = imgUrls.stream()
                .map(url -> {
                    ChatMessageImageDetailDto details = ChatMessageImageDetailDto.builder()
                            .image(url)
                            .type(request.getType())
                            .createdAt(LocalDateTime.now())
                            .build();
                    return details;
                })
                .collect(Collectors.toList());
        chatMessageService.saveChat(chatMessage);
        template.convertAndSend("/sub/public/" + chatMessage.getRoomId(), chatMessage);

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
    @GetMapping("/notice")
    public ResponseEntity<?> noticeList(@RequestParam("roomId") String roomId) {
        ChatRoom chatRoom = chatRoomService.findChatRoomById(roomId);
        List<ChatRoomNoticeListDto> noticeList = chatMessageService.setChatRoomNoticeListResponse(chatRoom).stream()
                .map(notice -> {
                    notice.setProfileImgUrl(memberRepository.findById(notice.getMemberId()).get().getImage());
                    return notice;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(noticeList);
    }

    //채팅방 나가기
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
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("memberId", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomId", chatMessage.getRoomId());

        return chatMessage;
    }
}
