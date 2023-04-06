package rest.api.mosk.product.manager.server.domain.model.category.dto;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.category.Category;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateCategoryRequest {

    private String name;


    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }
}
