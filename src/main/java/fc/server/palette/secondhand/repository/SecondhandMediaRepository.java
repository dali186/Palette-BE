package fc.server.palette.secondhand.repository;

import fc.server.palette.secondhand.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecondhandMediaRepository extends JpaRepository<Media, Long> {
    List<Media> findAllBySecondhand_id(Long secondhandId);

    Media findByUrl(String url);
}
