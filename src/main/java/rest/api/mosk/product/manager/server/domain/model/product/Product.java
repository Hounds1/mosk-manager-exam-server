package rest.api.mosk.product.manager.server.domain.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.category.Category;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_data_id")
    private FileData fileData;


    /**
     * 비즈니스 로직
     */

    public void setCategory(final Category category) {
        this.category = category;
    }
}
