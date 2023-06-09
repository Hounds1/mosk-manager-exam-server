package rest.api.mosk.product.manager.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.mosk.product.manager.server.api.ProductReadService;
import rest.api.mosk.product.manager.server.api.ProductService;
import rest.api.mosk.product.manager.server.domain.model.product.dto.CreateProductRequest;
import rest.api.mosk.product.manager.server.domain.model.product.dto.ProductAndCategoryResponse;
import rest.api.mosk.product.manager.server.domain.model.product.dto.SimpleProductResponse;
import rest.api.mosk.product.manager.server.global.dto.PageCustomResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ProductRestController {

    private final ProductService productService;
    private final ProductReadService productReadService;

    private static final int SIZE = 10;

    @PostMapping("/products")
    public ResponseEntity<SimpleProductResponse> create(@RequestBody final CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.create(request.toEntity(), request.getCategoryName()));
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/products/search")
    public ResponseEntity<ProductAndCategoryResponse> findByName(@RequestParam(name = "name") final String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productReadService.findProductWithCategory(name));
    }

    @GetMapping("/products/all")
    public ResponseEntity<PageCustomResponse<ProductAndCategoryResponse>> getPageWithStoreName(@RequestParam(name = "storeName")final String storeName,
                                                                                               @RequestParam(name = "page") final int page) {

        PageRequest pageRequest = PageRequest.of(page, SIZE);
        return ResponseEntity.status(HttpStatus.OK).body(productReadService.getPageWithStoreName(storeName, pageRequest));
    }

    public ResponseEntity<String> fallback() {
        String message = "불러올 수 없는 상태입니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }
}
