package fc.server.palette.chat.dto.request;

import lombok.Getter;

@Getter
public class ChatRoomNoticeRequestDto {
    private String roomId;
    private String messageId;
}
