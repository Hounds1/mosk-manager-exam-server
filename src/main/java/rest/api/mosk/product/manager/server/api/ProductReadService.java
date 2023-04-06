package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.product.Product;
import rest.api.mosk.product.manager.server.domain.model.product.dto.SimpleProductResponse;
import rest.api.mosk.product.manager.server.domain.model.product.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProductReadService {

    private final ProductRepository productRepository;

    public SimpleProductResponse findByName(final String name) {
        Product findProduct = productRepository.findByName(name).orElseThrow(
                () -> new IllegalStateException("불러올 수 없는 상태")
        );

        return SimpleProductResponse.of(findProduct);
    }

    public List<SimpleProductResponse> findAll() {
        List<Product> all = productRepository.findAll();

        return all.stream().map(SimpleProductResponse::of).collect(Collectors.toList());
    }
}
