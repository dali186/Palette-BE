package fc.server.palette.purchase.service;

import fc.server.palette.member.entity.Member;
import fc.server.palette.purchase.dto.request.EditOfferDto;
import fc.server.palette.purchase.dto.response.MemberDto;
import fc.server.palette.purchase.dto.response.OfferDto;
import fc.server.palette.purchase.entity.Bookmark;
import fc.server.palette.purchase.entity.Purchase;
import fc.server.palette.purchase.repository.PurchaseBookmarkRepository;
import fc.server.palette.purchase.repository.PurchaseMediaRepository;
import fc.server.palette.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMediaRepository purchaseMediaRepository;
    private final PurchaseBookmarkRepository purchaseBookmarkRepository;

    @Transactional
    public List<OfferDto> getAllOffers() {
        List<Purchase> purchases = purchaseRepository.findAll();

        return purchases.stream()
                .map(this::buildOffer)
                .collect(Collectors.toList());
    }

    @Transactional
    public OfferDto getOffer(Long offerId) {
        Purchase purchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return buildOffer(purchase);
    }

    @Transactional
    public OfferDto createOffer(Purchase purchase) {
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return buildOffer(savedPurchase);
    }

    private OfferDto buildOffer(Purchase purchase) {
        return OfferDto.builder()
                .id(purchase.getId())
                .member(MemberDto.of(purchase.getMember()))
                .title(purchase.getTitle())
                .category(purchase.getCategory())
                .startDate(purchase.getStartDate())
                .endDate(purchase.getEndDate())
                .price(purchase.getPrice())
                //todo: findById x -> findByPurchaseId o + 이미지 로직 처리 시 구현
//                .thumbnailUrl(purchaseMediaRepository.findById(purchase.getId())
//                        .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다.")).getUrl())
                .hits(purchase.getHits())
                .build();
    }

    @Transactional
    public void addBookmark(Long offerId, Member member) {
        Purchase purchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));

        Bookmark bookmark = Bookmark.builder()
                .member(member)
                .purchase(purchase)
                .build();
        purchaseBookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteOffer(Long offerId) {
        purchaseRepository.deleteById(offerId);
    }

    @Transactional
    public OfferDto editOffer(Long offerId, EditOfferDto editOfferDto) {
        Purchase purchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        purchase.updateOffer(editOfferDto);
        Purchase updatedPurchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return buildOffer(updatedPurchase);
    }

    @Transactional
    public OfferDto closeOffer(Long offerId){
        Purchase purchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        purchase.closeOffer();
        Purchase updatedPurchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return buildOffer(updatedPurchase);
    }

    @Transactional
    public Long getAuthorId(Long offerId){
        Purchase purchase = purchaseRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return purchase.getMember().getId();
    }
}
