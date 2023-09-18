package fc.server.palette.member.entity;

import fc.server.palette._common.auditing.BaseEntity;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import fc.server.palette.member.entity.type.Sex;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false, length = 50)
    private String email;

    @Column(nullable= false, length = 100)
    private String password;

    @Column(nullable= false, length = 30)
    private String name;

    @Column(length = 30)
    private String nickname;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(length = 50)
    private String bio;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    private Job job;

    private String image;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
