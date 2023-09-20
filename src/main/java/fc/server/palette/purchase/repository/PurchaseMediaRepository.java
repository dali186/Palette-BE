package fc.server.palette.purchase.repository;

import fc.server.palette.purchase.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseMediaRepository extends JpaRepository<Media, Long> {
    List<Media> findAllByPurchase_id(Long purchaseId);
}
