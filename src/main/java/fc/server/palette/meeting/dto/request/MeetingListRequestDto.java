package fc.server.palette.meeting.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fc.server.palette.meeting.entity.Media;
import fc.server.palette.meeting.entity.type.*;
import fc.server.palette.member.entity.Member;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import fc.server.palette.member.entity.type.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MeetingListRequestDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Member member;

    private Long id;
    private Category category;
    private Type type;
    private List<Job> jobs;
    private List<Position> positions;
    private Sex sex;
    private List<Age> ageRange;
    private List<Media> image;
    private String title;
    private String description;
    private int headCount;
    private Date startDate;
    private Date endDate;
    private boolean onOff;
    private String place;
    private Week week;
    private List<Day> days;
    private String time;
    private String progressTime;
    private AcceptType acceptType;
}
