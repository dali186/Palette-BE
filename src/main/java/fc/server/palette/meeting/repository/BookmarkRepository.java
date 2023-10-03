package fc.server.palette.meeting.repository;

import fc.server.palette.meeting.entity.Bookmark;
import fc.server.palette.meeting.entity.Meeting;
import fc.server.palette.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark findByMemberAndMeeting(Member member, Meeting meeting);

    Bookmark findByMemberIdAndMeeting(Long loginMember, Meeting meeting);
}
