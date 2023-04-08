package rest.api.mosk.product.manager.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rest.api.mosk.product.manager.server.api.FileDataReadService;
import rest.api.mosk.product.manager.server.api.FileDataService;
import rest.api.mosk.product.manager.server.domain.model.file.dto.SimpleFileResponse;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class FileDataRestController {

    private final FileDataService fileDataService;
    private final FileDataReadService fileDataReadService;

    @PostMapping("/files")
    public ResponseEntity<Void> create(@RequestParam(name = "files") final MultipartFile file, final String productName) throws IOException {
        fileDataService.create(file, productName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/files")
    public ResponseEntity<byte[]> readFile(@RequestParam(name = "fileId") final Long id) throws IOException{
        SimpleFileResponse res = fileDataReadService.readFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(res.getContentType()))
                .body(res.getFile());
    }
}
