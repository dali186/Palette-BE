package fc.server.palette.purchase.dto.request;

import fc.server.palette.purchase.entity.type.ClosingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditProductDto {
    private String shopUrl;
    private Date endDate;
    private Time endTime;
    private Integer headCount;
    private Integer Price;
    //todo 이미지 필드 추가
    private String description;
    private ClosingType closingType;
    private String bank;
    private String accountNumber;
    private String accountOwner;
}
