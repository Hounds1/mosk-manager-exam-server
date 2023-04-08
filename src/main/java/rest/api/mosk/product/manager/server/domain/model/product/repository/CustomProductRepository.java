package rest.api.mosk.product.manager.server.domain.model.product.repository;

import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;

import java.util.Optional;

public interface CustomProductRepository {
    Optional<ProductAndCategoryResponse> findProductWithCategory(final String name);
}
