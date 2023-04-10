package rest.api.mosk.product.manager.server.domain.model.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rest.api.mosk.product.manager.server.domain.model.category.QCategory;
import rest.api.mosk.product.manager.server.domain.model.file.QFileData;
import rest.api.mosk.product.manager.server.domain.model.product.QProduct;
import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;

import java.util.Optional;

import static rest.api.mosk.product.manager.server.domain.model.category.QCategory.*;
import static rest.api.mosk.product.manager.server.domain.model.file.QFileData.*;
import static rest.api.mosk.product.manager.server.domain.model.product.QProduct.*;

@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private JPAQueryFactory queryFactory;

    @Override
    public Optional<ProductAndCategoryResponse> findProductWithCategory(final String name) {
        return Optional.ofNullable(queryFactory.select(Projections.bean(ProductAndCategoryResponse.class,
                        product.name.as("productName"),
                        product.price,
                        category.name.as("categoryName"),
                        fileData.id.as("fileId")))
                .from(product)
                .leftJoin(product.category, category)
                .leftJoin(product.fileData ,fileData)
                .where(product.name.eq(name))
                .fetchOne());
    }
}
