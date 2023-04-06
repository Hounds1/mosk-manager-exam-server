package rest.api.mosk.product.manager.server.domain.model.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.mosk.product.manager.server.domain.model.category.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   Optional<Category> findByName(final String name);
}
