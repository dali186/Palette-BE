package fc.server.palette.meeting.service;

import fc.server.palette.meeting.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
