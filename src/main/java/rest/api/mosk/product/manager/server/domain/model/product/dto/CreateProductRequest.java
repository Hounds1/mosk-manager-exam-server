package rest.api.mosk.product.manager.server.domain.model.product.dto;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.product.Product;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateProductRequest {

    private String name;

    private int price;

    private String categoryName;


    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .build();
    }
}
