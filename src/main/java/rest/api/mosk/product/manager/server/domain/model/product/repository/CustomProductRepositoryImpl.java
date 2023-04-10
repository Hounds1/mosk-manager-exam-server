package rest.api.mosk.product.manager.server.domain.model.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import rest.api.mosk.product.manager.server.domain.model.category.QCategory;
import rest.api.mosk.product.manager.server.domain.model.file.QFileData;
import rest.api.mosk.product.manager.server.domain.model.product.QProduct;
import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;
import rest.api.mosk.product.manager.server.global.dto.PageCustomResponse;

import java.util.List;
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

    @Override
    public PageCustomResponse<ProductAndCategoryResponse> findProductWithCategoryByName(final String storeName, final Pageable pageable) {
        List<ProductAndCategoryResponse> products = queryFactory.select(Projections.constructor(ProductAndCategoryResponse.class,
                        product.name.as("productName"),
                        product.price,
                        product.storeName,
                        category.name.as("categoryName"),
                        fileData.id.as("fileId")))
                .from(product)
                .leftJoin(product.category, category)
                .leftJoin(product.fileData, fileData)
                .where(product.storeName.eq(storeName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (products.size() == 0) {
            return PageCustomResponse.of(Page.empty());
        }

        JPAQuery<Long> count = queryFactory.select(product.count())
                .from(product);

        return PageCustomResponse.of(PageableExecutionUtils.getPage(products, pageable, count::fetchFirst));
    }


}
