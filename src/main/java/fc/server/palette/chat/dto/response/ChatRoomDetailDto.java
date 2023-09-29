package fc.server.palette.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDetailDto {
    private Long host;
    private String notice;
    //개인 - false, 미팅,중고거래, 공동구매 - true
    private boolean isDelete;
    private ChatRoomDetailContentDto contentNotice;
}
