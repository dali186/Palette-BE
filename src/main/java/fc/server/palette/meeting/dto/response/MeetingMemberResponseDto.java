package fc.server.palette.meeting.dto.response;

import fc.server.palette.member.entity.Member;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import fc.server.palette.member.entity.type.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MeetingMemberResponseDto {
    private Long id;
    private String email;
    private String name;
    private String bio;
    private Position position;
    private Job job;
    private String image;
    private Sex sex;

    public MeetingMemberResponseDto(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.bio = member.getBio();
        this.position = member.getPosition();
        this.job = member.getJob();
        this.image = member.getImage();
        this.sex = member.getSex();
    }

}
