package com.pospayment.pospayment.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
            InputStreamResource resource;
            BufferedImage image;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            image = ImageIO.read(Files.newInputStream(file, StandardOpenOption.READ));
            ImageIO.write(image, "png", outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            resource = new InputStreamResource(inputStream);

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (IOException e) {
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
