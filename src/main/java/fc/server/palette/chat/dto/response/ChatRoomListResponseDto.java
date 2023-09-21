package fc.server.palette.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ChatRoomListResponseDto {
    private String id;
    private String title;
    private String image;
    private List<Long> memberList;
    private String recentMessage;
    private LocalDateTime recentTime;
    private Long unRead;
}
