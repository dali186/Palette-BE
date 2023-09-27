package fc.server.palette.meeting.dto.response;

import fc.server.palette.chat.dto.response.ChatRoomDetailContentDto;
import fc.server.palette.chat.entity.type.ChatRoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MeetingDetailResponseDto {
    private MeetingMemberResponseDto meetingMemberResponseDto;
    private Long id;
    private String category;
    private String type;
    private List<String> jobs;
    private List<String> positions;
    private String sex;
    private List<String> image;
    private String title;
    private String description;
    private int headCount;
    private int recruitedPersonnel;
    private Date startDate;
    private Date endDate;
    private boolean onOff;
    private String place;
    private String week;
    private List<String> days;
    private String time;
    private String progressTime;
    private String acceptType;
    private boolean isClosing;
    private Integer hits;
    private Integer likes;
    private LocalDateTime createdAt;

    public ChatRoomDetailContentDto toChatRoomInfo() {
        return ChatRoomDetailContentDto.builder()
                .contentId(id)
                .type(ChatRoomType.MEETING)
                .title(title)
                .week(week)
                .build();
    }
}
