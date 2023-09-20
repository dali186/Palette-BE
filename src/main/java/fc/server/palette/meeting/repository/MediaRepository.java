package fc.server.palette.meeting.repository;

import fc.server.palette.meeting.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
