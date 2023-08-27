package by.bsuir.andrei.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.Version.HTTP_1_1;

public class HttpClientRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newBuilder()
                .version(HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.google.com"))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
