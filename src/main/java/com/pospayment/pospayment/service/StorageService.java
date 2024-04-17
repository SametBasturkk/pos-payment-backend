package com.pospayment.pospayment.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
public class StorageService {

    private final Path rootLocation = Path.of("upload-dir");

    public StorageService() {
        init();
    }

    public String store(MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(Objects.requireNonNull(uuid.toString())));
            return uuid.toString();
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {

            boolean isFolderExist = Files.exists(rootLocation);
            if (!isFolderExist) {
                Files.createDirectory(rootLocation);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
