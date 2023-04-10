package rest.api.mosk.product.manager.server.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageCustomResponse<T> {

    private List<T> data = new ArrayList<>();
    private long totalPage;
    private int pageSize;
    private long totalElement;
    private int number;

    public static <T> PageCustomResponse<T> of(Page<T> response) {
        return new PageCustomResponse<>(
                response.getContent(),
                response.getTotalPages(),
                response.getSize(),
                response.getTotalElements(),
                response.getNumber()
        );
    }
}
