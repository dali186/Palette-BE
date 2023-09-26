package fc.server.palette.secondhand.controller;

import fc.server.palette._common.validation.MemberValidator;
import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.secondhand.dto.request.CreateProductDto;
import fc.server.palette.secondhand.dto.request.EditProductDto;
import fc.server.palette.secondhand.dto.response.ProductDto;
import fc.server.palette.secondhand.dto.response.ProductListDto;
import fc.server.palette.secondhand.entity.Media;
import fc.server.palette.secondhand.entity.Secondhand;
import fc.server.palette.secondhand.service.SecondhandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        ProductDto product = secondhandService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        userDetails.validateAuthority(secondhandService.getAuthorId(productId));
        secondhandService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{productId}/closing")
    public ResponseEntity<ProductDto> closeTransaction(@PathVariable Long productId,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        userDetails.validateAuthority(secondhandService.getAuthorId(productId));
        ProductDto product = secondhandService.closeTransaction(productId);
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto createProductDto,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails){
        Secondhand product = createProductDto.toEntity(userDetails.getMember());
        List<Media> mediaList = toMediaList(createProductDto.getImages(), product);
        ProductDto savedProduct = secondhandService.createProduct(product, mediaList);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    private List<Media> toMediaList(List<String> images, Secondhand secondhand) {
        return images.stream().map(url -> Media.builder()
                        .secondhand(secondhand)
                        .url(url)
                        .build())
                .collect(Collectors.toList());
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDto> EditProduct(@PathVariable Long productId,
                                                  @RequestBody EditProductDto editProductDto,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails){
        userDetails.validateAuthority(secondhandService.getAuthorId(productId));
        ProductDto product = secondhandService.editProduct(productId, editProductDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
