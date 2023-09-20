package fc.server.palette.purchase.dto.response;

import fc.server.palette.purchase.entity.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    //todo: 추가 데이터 필요하면 수정
    private Long id;
    private MemberDto member;
    private String title;
    private Category category;
    private Date endDate;
    private Time endTime;
    private Integer price;
    private String thumbnailUrl;
    private Integer hits;
}
