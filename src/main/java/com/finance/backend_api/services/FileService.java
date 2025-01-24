package com.finance.backend_api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private static final String UPLOAD_DIR = "uploads/";

    public String uploadFile(MultipartFile file)  {
        logger.info("Uploading file: {}", file.getOriginalFilename());

        // Crear el directorio de carga si no existe
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            logger.info("Upload directory does not exist. Creating directory: {}", UPLOAD_DIR);
            uploadDir.mkdirs();
        }

        // Generar un nombre único para el archivo
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename().replace(" ", "_");
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
        logger.info("File successfully uploaded to: {}", UPLOAD_DIR + fileName);

        // Generar la URL pública del archivo
        String fileUrl = "http://localhost:8080/" + UPLOAD_DIR + fileName;
        logger.info("File available at URL: {}", fileUrl);
        return fileUrl;
    }

    public boolean deleteFile(String fileUrl) throws IOException {
        logger.info("Deleting file: {}", fileUrl);

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        try {
            boolean deleted = Files.deleteIfExists(Paths.get(UPLOAD_DIR + fileName));
            if (deleted) {
                logger.info("File successfully deleted: {}", fileName);
                return true;
            } else {
                logger.warn("File not found: {}", fileName);
                return false;
            }
        } catch (IOException e) {
            logger.error("Failed to delete file: {}", fileName, e);
            throw e;
        }
    }

    public List<String> getFiles() {
        logger.info("Getting all files");

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            logger.info("Upload directory does not exist. No files found.");
            return new ArrayList<>();
        }

        File[] files = uploadDir.listFiles();
        List<String> fileUrls = new ArrayList<>();
        for (File file : files) {
            fileUrls.add("http://localhost:8080/" + UPLOAD_DIR + file.getName());
        }

        logger.info("Found {} files", fileUrls.size());
        return fileUrls;
    }
}
