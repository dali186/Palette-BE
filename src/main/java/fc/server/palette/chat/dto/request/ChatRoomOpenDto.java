package fc.server.palette.chat.dto.request;

import fc.server.palette.chat.entity.type.ChatRoomType;
import lombok.Getter;

@Getter
public class ChatRoomOpenDto {
    private ChatRoomType type;
    private Long contentId;
    private Long participant;
}
