package fc.server.palette.purchase.repository;

import fc.server.palette.purchase.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseBookmarkRepository extends JpaRepository<Bookmark, Long> {
}
