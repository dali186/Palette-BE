package fc.server.palette.chat.entity;

import fc.server.palette.chat.entity.type.ChatMessageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "chat")
@Builder
public class ChatMessage {
    @Id
    private String id;
    private ChatMessageType type;
    private String content;
    private List<String> image;
    private Long sender;
    private String roomId;
    private LocalDateTime createdAt;
}
