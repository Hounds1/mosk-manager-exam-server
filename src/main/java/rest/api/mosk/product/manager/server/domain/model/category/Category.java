package rest.api.mosk.product.manager.server.domain.model.category;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product_id")
    private List<Product> products;
}
