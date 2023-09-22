package fc.server.palette.secondhand.entity;

import fc.server.palette._common.auditing.BaseEntity;
import fc.server.palette.member.entity.Member;
import fc.server.palette.purchase.entity.type.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Secondhand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false, length = 50)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Time transactionStartTime;

    @Column(nullable = false)
    private Time transactionEndTime;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String description;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isSoldOut;

    @Column(nullable = false, length = 50)
    private String bank;

    @Column(nullable = false, length = 50)
    private String accountNumber;

    @Column(nullable = false, length = 50)
    private String accountOwner;

    @Column(nullable = false)
    private Integer hits;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Boolean isFree;

    public void closeTransaction() {
        this.isSoldOut = true;
    }
}
