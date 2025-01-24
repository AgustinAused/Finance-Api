package com.finance.backend_api.controllers;

import com.finance.backend_api.services.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File Controller", description = "API for managing files")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @DeleteMapping("/")
    public String deleteFile(@RequestParam("url") String url) throws IOException {
        return fileService.deleteFile(url) ? "File deleted successfully" : "File not found";
    }

    @GetMapping("/")
    public ResponseEntity<?> getFiles() {
        return ResponseEntity.ok().body(fileService.getFiles());
    }
}
