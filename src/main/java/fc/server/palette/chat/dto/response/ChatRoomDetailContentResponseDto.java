package fc.server.palette.chat.dto.response;

import fc.server.palette.chat.entity.type.ChatRoomType;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomDetailContentResponseDto {
    private Long contentId;
    private String title;
    private List<Object> tag;
    private String image;
    private Object week;
    private ChatRoomType type;
}
