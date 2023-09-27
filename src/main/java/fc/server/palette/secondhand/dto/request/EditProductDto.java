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
    private Integer price;
    private Time transactionStartTime;
    private Time transactionEndTime;
    private String description;
}

