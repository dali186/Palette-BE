package fc.server.palette.purchase.repository;

import fc.server.palette.purchase.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseMediaRepository extends JpaRepository<Media, Long> {
}
