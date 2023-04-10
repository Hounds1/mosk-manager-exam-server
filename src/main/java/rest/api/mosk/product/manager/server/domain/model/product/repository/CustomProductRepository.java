package rest.api.mosk.product.manager.server.domain.model.product.repository;

import org.springframework.data.domain.Pageable;
import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;
import rest.api.mosk.product.manager.server.global.dto.PageCustomResponse;

import java.util.Optional;

public interface CustomProductRepository {
    Optional<ProductAndCategoryResponse> findProductWithCategory(final String name);

    PageCustomResponse<ProductAndCategoryResponse> findProductWithCategoryByName(final String storeName, final Pageable pageable);
}
