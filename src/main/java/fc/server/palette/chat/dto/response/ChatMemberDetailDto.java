package fc.server.palette.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMemberDetailDto {
    private Long id;
    private String profileUrl;
    private String nickName;
}
