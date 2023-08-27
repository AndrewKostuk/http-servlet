package by.bsuir.andrei.http.service;

import by.bsuir.andrei.http.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();
    private static final String DEFAULT_LOCATION = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent) {
        var path = Path.of(DEFAULT_LOCATION, imagePath);
        try (imageContent) {
            Files.createDirectories(path.getParent());
            Files.write(path, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<InputStream> getImage(String imagePath) {
        var fullPath = Path.of(DEFAULT_LOCATION, imagePath);
        return Files.exists(fullPath)
                ? Optional.of(Files.newInputStream(fullPath))
                : Optional.empty();
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
