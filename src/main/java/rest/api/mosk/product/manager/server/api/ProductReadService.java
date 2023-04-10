package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.product.Product;
import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;
import rest.api.mosk.product.manager.server.domain.model.product.dto.SimpleProductResponse;
import rest.api.mosk.product.manager.server.domain.model.product.repository.ProductRepository;
import rest.api.mosk.product.manager.server.global.dto.PageCustomResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProductReadService {

    private final ProductRepository productRepository;

    public SimpleProductResponse findByName(final String name) {
        Product findProduct = productRepository.findByName(name).orElseThrow(
                () -> new IllegalStateException("불러올 수 없는 상태")
        );

        return SimpleProductResponse.of(findProduct);
    }

    public ProductAndCategoryResponse findProductWithCategory(final String name) {
        return productRepository.findProductWithCategory(name)
                .orElseThrow( () -> new IllegalStateException("찾을 수 없음"));
    }

    public PageCustomResponse<ProductAndCategoryResponse> getPageWithStoreName(final String storeName, final PageRequest pageRequest) {
        return productRepository.findProductWithCategoryByName(storeName, pageRequest);
    }
}
