package fc.server.palette.secondhand.controller;

import fc.server.palette.secondhand.dto.ProductDto;
import fc.server.palette.secondhand.dto.ProductListDto;
import fc.server.palette.secondhand.service.SecondhandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secondhand")
@RequiredArgsConstructor
public class SecondhandController {
    private final SecondhandService secondhandService;

    @GetMapping("")
    public ResponseEntity<List<ProductListDto>> getAllProducts() {
        List<ProductListDto> products = secondhandService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        ProductDto product = secondhandService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        secondhandService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
