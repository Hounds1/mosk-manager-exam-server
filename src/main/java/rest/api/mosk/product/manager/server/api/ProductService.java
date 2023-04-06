package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.category.Category;
import rest.api.mosk.product.manager.server.domain.model.category.repository.CategoryRepository;
import rest.api.mosk.product.manager.server.domain.model.product.Product;
import rest.api.mosk.product.manager.server.domain.model.product.dto.SimpleProductResponse;
import rest.api.mosk.product.manager.server.domain.model.product.repository.ProductRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SimpleProductResponse create(final Product product, final String categoryName) {
        Category findCategory = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new IllegalStateException("불러올 수 없는 상태")
        );

        product.setCategory(findCategory);

        Product savedProduct = productRepository.save(product);

        return SimpleProductResponse.of(savedProduct);
    }
}
