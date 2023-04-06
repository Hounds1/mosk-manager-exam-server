package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;
import rest.api.mosk.product.manager.server.domain.model.file.dto.SimpleFileResponse;
import rest.api.mosk.product.manager.server.domain.model.file.repository.FileDataRepository;
import rest.api.mosk.product.manager.server.domain.model.product.Product;
import rest.api.mosk.product.manager.server.domain.model.product.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileDataService {

    private final FileDataRepository fileDataRepository;
    private final ProductRepository productRepository;

    private static final String LOCAL_PATH = "C:\\Users\\Student\\Desktop\\study\\imgs";

    public SimpleFileResponse create(final MultipartFile file, final String productName) throws IOException {

        String randomFileName = UUID.randomUUID().toString();
        String newFilePath = LOCAL_PATH + randomFileName;

        try {
            file.transferTo(new File(newFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product findProduct = productRepository.findByName(productName)
                .orElseThrow( () -> new IllegalStateException("불러올 수 없는 상태"));

        FileData newFileData = FileData.builder()
                    .fileName(randomFileName)
                    .filePath(newFilePath)
                    .contentType(file.getContentType())
                    .product(findProduct)
                    .build();

        if (newFileData != null) {
            FileData savedFileData = fileDataRepository.save(newFileData);
            return SimpleFileResponse.of(savedFileData);
        } else throw new IllegalStateException("저장할 수 없는 상태");
    }
}
