package rest.api.mosk.product.manager.server.domain.model.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
}
