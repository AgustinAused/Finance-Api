package com.finance.backend_api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Received a file upload request. File name: {}", file.getOriginalFilename());

        // Verificar si el directorio de carga existe, si no, crearlo.
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            logger.info("Upload directory does not exist. Creating directory: {}", UPLOAD_DIR);
            uploadDir.mkdirs();
        }

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + fileName));
            logger.info("File successfully uploaded to: {}", UPLOAD_DIR + fileName);
        } catch (IOException e) {
            logger.error("Failed to upload file: {}", file.getOriginalFilename(), e);
            throw e;
        }

        String fileUrl = "http://localhost:8080/" + UPLOAD_DIR + fileName;
        logger.info("File available at URL: {}", fileUrl);
        return fileUrl;
    }

    @DeleteMapping("/")
    public String deleteFile(@RequestParam("url") String url) throws IOException {
        logger.info("Received a file delete request. File URL: {}", url);

        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try {
            boolean deleted = Files.deleteIfExists(Paths.get(UPLOAD_DIR + fileName));
            if (deleted) {
                logger.info("File successfully deleted: {}", fileName);
                return "File deleted";
            } else {
                logger.warn("File not found, unable to delete: {}", fileName);
                return "File not found";
            }
        } catch (IOException e) {
            logger.error("Failed to delete file: {}", fileName, e);
            throw e;
        }
    }
}
