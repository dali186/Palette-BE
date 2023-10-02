package fc.server.palette.chat.dto.response;

import fc.server.palette.chat.entity.type.ChatMessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatMessageImageDetailDto {
    private String image;
    private ChatMessageType type;
    private LocalDateTime createdAt;
}
