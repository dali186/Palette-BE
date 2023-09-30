package fc.server.palette.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatRoomNoticeListDto {
    private String noticeId;
    private String notice;
    private Long memberId;
    private String profileImgUrl;
    private LocalDateTime createdAt;
    private Long host;
}
