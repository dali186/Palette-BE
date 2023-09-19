package fc.server.palette.purchase.service;

import fc.server.palette.purchase.dto.request.EditProductDto;
import fc.server.palette.purchase.dto.response.MemberDto;
import fc.server.palette.purchase.dto.response.ProductDto;
import fc.server.palette.purchase.entity.Bookmark;
import fc.server.palette.purchase.entity.Purchase;
import fc.server.palette.purchase.repository.PurchaseBookmarkRepository;
import fc.server.palette.purchase.repository.PurchaseMediaRepository;
import fc.server.palette.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMediaRepository purchaseMediaRepository;
    private final PurchaseBookmarkRepository purchaseBookmarkRepository;

    @Transactional
    public List<ProductDto> getAllProducts() {
        List<Purchase> purchases = purchaseRepository.findAll();

        return purchases.stream()
                .map(this::buildProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto getProduct(Long productId) {
        Purchase purchase = purchaseRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return buildProduct(purchase);
    }

    @Transactional
    public ProductDto createProduct(Purchase purchase) {
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return buildProduct(savedPurchase);
    }

    private ProductDto buildProduct(Purchase purchase) {
        return ProductDto.builder()
                .id(purchase.getId())
                .member(MemberDto.of(purchase.getMember()))
                .title(purchase.getTitle())
                .category(purchase.getCategory())
                .endDate(purchase.getEndDate())
                .endTime(purchase.getEndTime())
                .price(purchase.getPrice())
                .thumbnailUrl(purchaseMediaRepository.findById(purchase.getId())
                        .orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다.")).getUrl())
                .hits(purchase.getHits())
                .build();
    }

    //todo principal사용
    @Transactional
    public void addBookmark(Long productId) {
        Purchase purchase = purchaseRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));

        Bookmark bookmark = Bookmark.builder()
                .member()
                .purchase(purchase)
                .build();
        purchaseBookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        purchaseRepository.deleteById(productId);
    }

    @Transactional
    public ProductDto editProduct(Long productId, EditProductDto editProductDto){
        //todo 멤버검증
        Purchase purchase = purchaseRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        purchase.update(editProductDto);
        Purchase updatedPurchase = purchaseRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("공동구매 객체가 존재하지 않습니다."));
        return buildProduct(updatedPurchase);

    }
}
