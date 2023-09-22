package fc.server.palette.secondhand.service;

import fc.server.palette.secondhand.dto.ProductListDto;
import fc.server.palette.secondhand.entity.Secondhand;
import fc.server.palette.secondhand.repository.SecondhandRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecondhandService {
    private final SecondhandRespository secondhandRespository;

    @Transactional(readOnly = true)
    public List<ProductListDto> getAllProducts(){
        List<Secondhand> products = secondhandRespository.findAll();
        return products.stream()
                .map(this::buildProductList)
                .collect(Collectors.toList());
    }

    private ProductListDto buildProductList(Secondhand secondhand){
        return ProductListDto.builder()
                .id(secondhand.getId())
                .title(secondhand.getTitle())
                .category(secondhand.getCategory())
                .price(secondhand.getPrice())
                .thumbnailUrl(secondhand.)
                .bookmarkCount()
                .hits(secondhand.getHits())
                .build();
    }
}
