package by.bsuir.andrei.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.net.http.HttpRequest.BodyPublishers.ofFile;


public class HttpClientRunner {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        var client = HttpClient.newBuilder()
                .version(HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000"))
                .POST(ofFile(Path.of("resources", "first.json")))
                .setHeader("accept", "text/html")
                .setHeader("content-type", "application/json")
                .build();

        var response1 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        var response2 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        var response3 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        var response4 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response4.get().body());
    }
}