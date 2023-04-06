package rest.api.mosk.product.manager.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.mosk.product.manager.server.api.ProductReadService;
import rest.api.mosk.product.manager.server.api.ProductService;
import rest.api.mosk.product.manager.server.domain.model.product.dto.CreateProductRequest;
import rest.api.mosk.product.manager.server.domain.model.product.dto.SimpleProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ProductRestController {

    private final ProductService productService;
    private final ProductReadService productReadService;

    @PostMapping("/products")
    public ResponseEntity<SimpleProductResponse> create(@RequestBody final CreateProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.create(request.toEntity(), request.getCategoryName()));
    }

    @GetMapping("/products/search")
    public ResponseEntity<SimpleProductResponse> findByName(@RequestParam(name = "name") final String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productReadService.findByName(name));
    }

    @GetMapping("/products/all")
    public ResponseEntity<List<SimpleProductResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productReadService.findAll());
    }
}
