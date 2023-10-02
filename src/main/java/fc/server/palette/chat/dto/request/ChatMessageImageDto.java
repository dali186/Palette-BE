package fc.server.palette.chat.dto.request;

import fc.server.palette.chat.entity.type.ChatMessageType;
import lombok.Getter;

@Getter
public class ChatMessageImageDto {
    private String roomId;
    private ChatMessageType type;
}
