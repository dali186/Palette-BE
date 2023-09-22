package fc.server.palette.secondhand.service;

import fc.server.palette.secondhand.dto.ProductListDto;
import fc.server.palette.secondhand.entity.Media;
import fc.server.palette.secondhand.entity.Secondhand;
import fc.server.palette.secondhand.repository.SecondhandBookmarkRepository;
import fc.server.palette.secondhand.repository.SecondhandMediaRepository;
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
    private final SecondhandMediaRepository secondhandMediaRepository;
    private final SecondhandBookmarkRepository secondhandBookmarkRepository;

    @Transactional(readOnly = true)
    public List<ProductListDto> getAllProducts() {
        List<Secondhand> products = secondhandRespository.findAll();
        return products.stream()
                .map(this::buildProductList)
                .collect(Collectors.toList());
    }

    private ProductListDto buildProductList(Secondhand secondhand) {
        return ProductListDto.builder()
                .id(secondhand.getId())
                .title(secondhand.getTitle())
                .category(secondhand.getCategory())
                .price(secondhand.getPrice())
                .thumbnailUrl(getThumbnailUrl(secondhand.getId()))
                .bookmarkCount(getBookmarkCount(secondhand.getId()))
                .hits(secondhand.getHits())
                .build();
    }

    private String getThumbnailUrl(Long secondhandId) {
        List<Media> mediaList = secondhandMediaRepository.findAllBySecondhand_id(secondhandId);
        if (mediaList.isEmpty()) {
            //todo 예외발생으로 변경
            return null;
        }
        return secondhandMediaRepository.findAllBySecondhand_id(secondhandId)
                .get(0)
                .getUrl();
    }

    private Integer getBookmarkCount(Long secondhandId) {
        return secondhandBookmarkRepository.findAllBySecondhand_id(secondhandId).size();
    }
}
