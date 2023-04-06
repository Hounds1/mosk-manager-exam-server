package rest.api.mosk.product.manager.server.domain.model.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.mosk.product.manager.server.domain.model.product.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(final String name);
}
