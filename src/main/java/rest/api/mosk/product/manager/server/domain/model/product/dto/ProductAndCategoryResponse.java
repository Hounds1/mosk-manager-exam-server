package rest.api.mosk.product.manager.server.domain.model.product.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProductAndCategoryResponse {

    private String productName;

    private int price;

    private String CategoryName;

    private Long fileId;

}
