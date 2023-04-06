package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.category.Category;
import rest.api.mosk.product.manager.server.domain.model.category.dto.SimpleCategoryResponse;
import rest.api.mosk.product.manager.server.domain.model.category.repository.CategoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public SimpleCategoryResponse create(final Category category) {
        Category savedCategory = categoryRepository.save(category);

        return SimpleCategoryResponse.of(savedCategory);
    }
}
