package fc.server.palette.chat.dto.response;

import fc.server.palette.chat.entity.type.ChatRoomType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChatRoomDetailContentDto {
    private Long contentId;
    private ChatRoomType type;
    private String title;
    private String image;

    private String week;
    private Integer price;
}
