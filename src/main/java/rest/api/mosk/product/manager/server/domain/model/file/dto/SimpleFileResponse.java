package rest.api.mosk.product.manager.server.domain.model.file.dto;

import lombok.*;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleFileResponse {

    private byte[] file;

    private String contentType;

    /**
     * 비즈니스 로직
     */

    public static SimpleFileResponse of(final FileData fileData) throws IOException {
        byte[] bytes = Files.readAllBytes(new File(fileData.getFileName()).toPath());

        return SimpleFileResponse.builder()
                .file(bytes)
                .contentType(fileData.getContentType())
                .build();
    }
}
