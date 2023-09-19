package fc.server.palette.purchase.controller;

import fc.server.palette.purchase.dto.request.EditProductDto;
import fc.server.palette.purchase.dto.request.OfferProductDto;
import fc.server.palette.purchase.dto.response.ProductDto;
import fc.server.palette.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupPurchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = purchaseService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        ProductDto product = purchaseService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductDto> offerProduct(@RequestBody OfferProductDto offerProductDto) {
        ProductDto product = purchaseService.createProduct(offerProductDto.toEntity());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/{productId}/bookmark")
    public ResponseEntity<?> addBookmark(@PathVariable Long productId) {
        //todo pricipal넘기기
        purchaseService.addBookmark(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        purchaseService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable Long productId, @RequestBody EditProductDto editProductDto){
        ProductDto product = purchaseService.editProduct(productId, editProductDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
