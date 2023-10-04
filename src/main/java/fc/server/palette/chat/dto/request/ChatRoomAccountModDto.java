package fc.server.palette.chat.dto.request;

import fc.server.palette.purchase.entity.type.ClosingType;
import lombok.Getter;

@Getter
public class ChatRoomAccountModDto {
    //DATETIME, HEAD_COUNT
    private ClosingType closingType;
    private String bank;
    private String accountNumber;
    private String accountOwner;
}
