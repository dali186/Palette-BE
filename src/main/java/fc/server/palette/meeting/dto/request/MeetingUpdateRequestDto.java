package fc.server.palette.meeting.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MeetingUpdateRequestDto {
    // private List<Media> image;
    private String title;
    private String description;
    private int headCount;
    private Date startDate;
    private Date endDate;
    private boolean onOff;
    private String place;
    private String week;
    private List<String> days;
    private String time;
    private String progressTime;
    private String acceptType;
}
