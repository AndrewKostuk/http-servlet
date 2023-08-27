package by.bsuir.andrei.http.servlet;

import by.bsuir.andrei.http.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

import static by.bsuir.andrei.http.util.UrlPath.IMAGES;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(IMAGES + "/*")
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestURI = req.getRequestURI();
        var imagePath = requestURI.replaceAll("/images", "");
        var optionalImage = imageService.getImage(imagePath);
        optionalImage.ifPresentOrElse(inputStream -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(inputStream, resp);
                },
                () -> resp.setStatus(SC_NOT_FOUND));
    }

    @SneakyThrows
    private void writeImage(InputStream inputStream, HttpServletResponse resp) {
        try (inputStream;
             var outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = inputStream.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
