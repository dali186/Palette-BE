package fc.server.palette.purchase.repository;

import fc.server.palette.purchase.entity.PurchaseParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseParticipantRepository extends JpaRepository<PurchaseParticipant, Long> {
    List<PurchaseParticipant> findAllByPurchaseId(Long purchaseId);
    }
