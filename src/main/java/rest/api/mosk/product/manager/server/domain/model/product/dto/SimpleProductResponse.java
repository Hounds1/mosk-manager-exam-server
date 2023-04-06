package rest.api.mosk.product.manager.server.domain.model.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.category.Category;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;
import rest.api.mosk.product.manager.server.domain.model.product.Product;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleProductResponse {

    private Long id;

    private String name;

    private int price;
    @JsonIgnore
    private Category category;

    public static SimpleProductResponse of(final Product product) {
        return SimpleProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
