package fc.server.palette.chat.entity;

import fc.server.palette._common.auditing.BaseEntity;
import fc.server.palette.chat.entity.type.ChatMessageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chat")
@Builder
public class ChatMessage extends BaseEntity {
    @Id
    private String id;
    private ChatMessageType type;
    private String content;
    private String image;
    private Long sender;
    private String roomId;
}
