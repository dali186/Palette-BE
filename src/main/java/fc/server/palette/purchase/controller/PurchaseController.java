package fc.server.palette.purchase.controller;

import fc.server.palette.purchase.dto.response.ProductDto;
import fc.server.palette.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    @GetMapping("/groupPurchase")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = purchaseService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
