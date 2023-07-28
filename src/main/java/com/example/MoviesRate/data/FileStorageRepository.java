package com.example.MoviesRate.data;

import com.example.MoviesRate.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Repository
public class FileStorageRepository {

    @Value("${STORAGE_FOLDER}")
    private String storageFolder;

    private Path getPath(String originalFilename) {
        Path filePath = Path.of(storageFolder).resolve(originalFilename).normalize();
        return filePath;
    }

    public void save(String originalFilename, InputStream inputStream) {
        try {
            Path filePath = getPath(originalFilename);
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }


    public Resource findByName(String filename) {
        try {
            Path filePath = getPath(filename);
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e);
        }
    }

    public void deleteByName(Optional<String> posterName) throws IOException {
        Path filePath = Path.of(storageFolder).resolve(String.valueOf(posterName)).normalize();
        Files.deleteIfExists(filePath);
    }
}
