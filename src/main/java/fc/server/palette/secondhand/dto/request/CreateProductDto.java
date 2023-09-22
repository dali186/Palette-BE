package fc.server.palette.secondhand.dto.request;

import fc.server.palette.member.entity.Member;
import fc.server.palette.purchase.entity.type.Category;
import fc.server.palette.secondhand.entity.Secondhand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductDto {
    private String title;
    private Category category;
    private Time transactionStartTime;
    private Time transactionEndTime;
    //todo 요일 필드
    private List<String> images;
    private Boolean isFree;
    private String description;
    private Integer price;

    public Secondhand toEntity(Member member){
        return Secondhand.builder()
                .member(member)
                .title(this.title)
                .category(this.category)
                .transactionStartTime(this.transactionStartTime)
                .transactionEndTime(this.transactionEndTime)
                .price(this.price)
                .description(this.description)
                .build();
    }
}
