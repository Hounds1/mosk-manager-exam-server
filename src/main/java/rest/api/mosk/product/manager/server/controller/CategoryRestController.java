package rest.api.mosk.product.manager.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.mosk.product.manager.server.api.CategoryReadService;
import rest.api.mosk.product.manager.server.api.CategoryService;
import rest.api.mosk.product.manager.server.domain.model.category.dto.CreateCategoryRequest;
import rest.api.mosk.product.manager.server.domain.model.category.dto.SimpleCategoryResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryReadService categoryReadService;

    @PostMapping("/categories")
    public ResponseEntity<SimpleCategoryResponse> create(@RequestBody final CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request.toEntity()));
    }

    @GetMapping("/categories/search")
    public ResponseEntity<SimpleCategoryResponse> findByName(@RequestParam(name = "name")final String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryReadService.findByName(name));
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<SimpleCategoryResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryReadService.findAll());
    }
}
