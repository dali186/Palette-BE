package fc.server.palette.chat.dto.response;

import lombok.Getter;

@Getter
public class ChatRoomDetailResponseDto {
    private Long host;
    private String notice;
    private ChatRoomDetailContentResponseDto contentNotice;
}
