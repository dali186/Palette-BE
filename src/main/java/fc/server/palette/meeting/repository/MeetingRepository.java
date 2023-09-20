package fc.server.palette.meeting.repository;

import fc.server.palette.meeting.entity.Meeting;
import fc.server.palette.meeting.entity.type.Type;
import fc.server.palette.member.entity.type.Job;
import fc.server.palette.member.entity.type.Position;
import fc.server.palette.member.entity.type.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByOnOffAndIsClosingFalse(boolean onOff);

    List<Meeting> findByTypeAndIsClosingFalse(Type type);

    List<Meeting> findByJobInAndIsClosingFalse(List<Job> jobs);

    List<Meeting> findByPositionInAndIsClosingFalse(List<Position> positions);

    List<Meeting> findBySexAndIsClosingFalse(Sex sex);

    List<Meeting> findTop2ByIsClosingFalseOrderByLikesDesc();
}
