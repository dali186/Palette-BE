package fc.server.palette.chat.dto.response;

import fc.server.palette.chat.entity.type.ChatMessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChatMessagesDto {
    private String messageId;
    private Long memberId;
    private String profileImgUrl;
    private String nickName;
    private String text;
    private List<String> image;
    private LocalDateTime createdAt;
    private ChatMessageType type;
}
