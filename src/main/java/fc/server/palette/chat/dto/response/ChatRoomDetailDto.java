package fc.server.palette.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDetailDto {
    private Long host;
    private String notice;
    private ChatRoomDetailContentDto contentNotice;
}
