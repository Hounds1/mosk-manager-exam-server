package rest.api.mosk.product.manager.server.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.mosk.product.manager.server.domain.model.file.FileData;
import rest.api.mosk.product.manager.server.domain.model.file.dto.SimpleFileResponse;
import rest.api.mosk.product.manager.server.domain.model.file.repository.FileDataRepository;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FileDataReadService {

    private final FileDataRepository fileDataRepository;

    public SimpleFileResponse readFile(final Long id) throws IOException {
        FileData findFileData = fileDataRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("불러올 수 없는 상태"));

        return SimpleFileResponse.of(findFileData);
    }
}
