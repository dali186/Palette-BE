package fc.server.palette.purchase.dto.request;

import fc.server.palette.purchase.entity.Purchase;
import fc.server.palette.purchase.entity.type.Category;
import fc.server.palette.purchase.entity.type.ClosingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferProductDto {
    private String title;
    private Category category;
    private String shopUrl;
    private Date endDate;
    private Time endTime;
    private Integer headCount;
    private Integer price;
    //todo 이미지 필드 추가
    private String description;
    private ClosingType closingType;
    private String bank;
    private String accountNumber;
    private String accountOwner;

    public Purchase toEntity(){
        return Purchase.builder()
                .title(this.title)
                .category(this.category)
                .shopUrl(this.shopUrl)
                .endDate(this.endDate)
                .endTime(this.endTime)
                .headCount(this.headCount)
                .price(this.price)
                .description(this.description)
                .closingType(this.closingType)
                .bank(this.bank)
                .accountNumber(this.accountNumber)
                .accountOwner(this.accountOwner)
                .build();
    }
}
