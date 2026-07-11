package org.sanosysalvos.controller;

import lombok.RequiredArgsConstructor;
import org.sanosysalvos.dto.UploadResponseDto;
import org.sanosysalvos.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/upload/{idReporte}")
    public ResponseEntity<UploadResponseDto> upload(
            @PathVariable Long idReporte,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        String url = storageService.uploadFile(file, idReporte);
        String key = url.substring(url.indexOf("reportes/"));
        return ResponseEntity.ok(new UploadResponseDto(url, key, idReporte));
    }
}