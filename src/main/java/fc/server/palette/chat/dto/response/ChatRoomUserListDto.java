package fc.server.palette.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomUserListDto {
    private Long memberId;
    private String profileImgUrl;
    private String nickName;
    private Long host;
}
