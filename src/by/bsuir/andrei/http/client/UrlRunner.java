package by.bsuir.andrei.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlRunner {

    public static void main(String[] args) throws IOException {
        getURLBySpec("https://www.google.com");
        getURLBySpec("file:D:\\IdeaProjects\\http-servlet\\src\\by\\bsuir\\andrei\\http\\socket\\DatagramRunner.java");
    }

    private static void getURLBySpec(String spec) throws IOException {
        var url = new URL(spec);
        var urlConnection = url.openConnection();
        try (var inputStream = urlConnection.getInputStream()) {
            System.out.println(new String(inputStream.readAllBytes()));
        }
    }
}
