package com.finance.backend_api.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final String UPLOAD_DIR = "/uploads/";

    @PostMapping("/")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + fileName));
        return "http://localhost:8080/uploads/" + fileName;
    }


    @DeleteMapping("/")
    public String deleteFile(@RequestParam("url") String url) throws IOException {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        Files.deleteIfExists(Paths.get(UPLOAD_DIR + fileName));
        return "File deleted";
    }



}
