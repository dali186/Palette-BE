package fc.server.palette.secondhand.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Time;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EditProductDto {
    //todo 협의 후 추가하거나 뺄 필드들은 수정 요망
    private Integer price;
    private Time transactionStartTime;
    private Time transactionEndTime;
    private List<String> images;
    private String description;
}

