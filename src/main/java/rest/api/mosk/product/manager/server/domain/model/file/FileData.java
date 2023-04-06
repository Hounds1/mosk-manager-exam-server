package rest.api.mosk.product.manager.server.domain.model.file;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.product.Product;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FileData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_data_id")
    private Long id;

    private String fileName;

    private String contentType;

    private String filePath;

    @OneToOne(mappedBy = "product_id")
    private Product product;
}
