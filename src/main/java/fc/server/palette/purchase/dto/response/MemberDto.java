package fc.server.palette.purchase.dto.response;

import fc.server.palette.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String nickname;
    private String bio;
    private String image;

    public static MemberDto of(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .bio(member.getBio())
                .image(member.getImage())
                .build();
    }

}

