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
    private String nickname;
    private String bio;
    private String image;

    public MeetingMemberResponseDto(Member member) {
        this.nickname = member.getNickname();
        this.bio = member.getBio();
        this.image = member.getImage();
    }
}
