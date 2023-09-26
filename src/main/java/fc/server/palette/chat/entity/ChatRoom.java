package fc.server.palette.chat.entity;

import fc.server.palette.chat.entity.type.ChatRoomType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "chatRoom")
public class ChatRoom {
    @Id
    private String id;
    private ChatRoomType type;
    private String title;
    private String thumbnail;
    private Long host;
    private List<String> noticeList;
    private Long contentId;
    private List<Long> memberList;
    private Map<Long, LocalDateTime> exitList;

    public ChatRoom() {
        this.memberList = new ArrayList<>();
        this.exitList = new HashMap<>();
        this.noticeList = new ArrayList<>();
    }
}
