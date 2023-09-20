package fc.server.palette.purchase.repository;

import fc.server.palette.purchase.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseBookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByPurchase_id(Long productId);
}
