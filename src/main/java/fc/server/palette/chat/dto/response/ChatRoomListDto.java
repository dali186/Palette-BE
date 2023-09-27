package fc.server.palette.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatRoomListDto {
    private String id;
    private String title;
    private String image;
    private Integer members;
    private String recentMessage;
    private LocalDateTime recentTime;
    private Long unRead;
}
