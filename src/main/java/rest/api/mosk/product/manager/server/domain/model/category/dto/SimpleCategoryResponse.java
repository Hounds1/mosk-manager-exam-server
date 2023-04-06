package rest.api.mosk.product.manager.server.domain.model.category.dto;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.category.Category;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleCategoryResponse {

    private Long id;

    private String name;

    public static SimpleCategoryResponse of(final Category category) {
        return SimpleCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
