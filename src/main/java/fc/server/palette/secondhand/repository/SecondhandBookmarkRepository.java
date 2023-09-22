package fc.server.palette.secondhand.repository;

import fc.server.palette.secondhand.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecondhandBookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllBySecondhand_id(Long secondhandId);
}
