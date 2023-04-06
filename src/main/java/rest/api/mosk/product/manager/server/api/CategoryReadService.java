package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.category.Category;
import rest.api.mosk.product.manager.server.domain.model.category.dto.SimpleCategoryResponse;
import rest.api.mosk.product.manager.server.domain.model.category.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CategoryReadService {

    private final CategoryRepository categoryRepository;

    public SimpleCategoryResponse findByName(final String name) {
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new IllegalStateException("불러올 수 없는 상태")
        );

        return SimpleCategoryResponse.of(findCategory);
    }

    public List<SimpleCategoryResponse> findAll() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(SimpleCategoryResponse::of).collect(Collectors.toList());
    }
}
