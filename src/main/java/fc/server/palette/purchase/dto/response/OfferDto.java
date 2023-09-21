package fc.server.palette.purchase.dto.response;

import fc.server.palette.purchase.entity.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private Long id;
    private MemberDto member;
    private String title;
    private Category category;
    private String description;
    private String shopUrl;
    private Integer headCount;
    private Integer bookmarkCount;
    private Integer currentParticipantCount;
    private List<String> image;
    private Date startDate;
    private Date endDate;
    private Integer price;
    private Boolean isClosing;
    private Integer hits;
    private LocalDateTime created_at;
}
