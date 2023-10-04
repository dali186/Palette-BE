package fc.server.palette.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomAccountDto {
    private String bank;
    private String accountNumber;
    private String accountOwner;
}
