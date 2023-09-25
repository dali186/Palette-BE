package fc.server.palette.purchase.controller;

import fc.server.palette._common.validation.MemberValidator;
import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.purchase.dto.request.EditOfferDto;
import fc.server.palette.purchase.dto.request.GroupPurchaseOfferDto;
import fc.server.palette.purchase.dto.response.OfferDto;
import fc.server.palette.purchase.dto.response.OfferListDto;
import fc.server.palette.purchase.entity.Media;
import fc.server.palette.purchase.entity.Purchase;
import fc.server.palette.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groupPurchase")
@RequiredArgsConstructor
@Slf4j
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("")
    public ResponseEntity<List<OfferListDto>> getAllOffers() {
        List<OfferListDto> offers = purchaseService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable Long offerId) {
        OfferDto offer = purchaseService.getOffer(offerId);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OfferDto> createOffer(@RequestBody GroupPurchaseOfferDto groupPurchaseOfferDto,
                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        Purchase purchase = groupPurchaseOfferDto.toEntity(userDetails.getMember());
        List<Media> mediaList = toMediaList(groupPurchaseOfferDto.getImages(), purchase);
        OfferDto offer = purchaseService.createOffer(purchase, mediaList);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    private List<Media> toMediaList(List<String> images, Purchase purchase) {
        return images.stream().map(url -> Media.builder()
                        .purchase(purchase)
                        .url(url)
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/{offerId}/bookmark")
    public ResponseEntity<?> addBookmark(@PathVariable Long offerId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        purchaseService.addBookmark(offerId, userDetails.getMember());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long offerId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        MemberValidator.validateAuthority(userDetails.getMember().getId(), purchaseService.getAuthorId(offerId));
        purchaseService.deleteOffer(offerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{offerId}")
    public ResponseEntity<OfferDto> editOffer(@PathVariable Long offerId,
                                              @RequestBody EditOfferDto editOfferDto,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        MemberValidator.validateAuthority(userDetails.getMember().getId(), purchaseService.getAuthorId(offerId));
        OfferDto offer = purchaseService.editOffer(offerId, editOfferDto);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("/{offerId}/closing")
    public ResponseEntity<OfferDto> closeOffer(@PathVariable Long offerId,
                                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        MemberValidator.validateAuthority(userDetails.getMember().getId(), purchaseService.getAuthorId(offerId));
        OfferDto offer = purchaseService.closeOffer(offerId);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
}
