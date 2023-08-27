package by.bsuir.andrei.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;


public class HttpServer {

    private final int port;
    private final ExecutorService pool;
    private boolean stopped;


    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
        this.stopped = false;
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            while (!stopped) {
                var socket = server.accept();
                System.out.println("Socket is accepted");
                pool.submit(() -> processRequest(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processRequest(Socket socket) {
        try (socket;
             var inputStream = socket.getInputStream();
             var outputStream = socket.getOutputStream()) {
            System.out.println("request: " + new String(inputStream.readNBytes(400)));
            String body = Files.readString(Path.of("resources", "example.html"));
            String headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length());
            Thread.sleep(10000);
            outputStream.write(headers.getBytes(UTF_8));
            outputStream.write(System.lineSeparator().getBytes(UTF_8));
            outputStream.write(body.getBytes(UTF_8));
        } catch (IOException | InterruptedException e) {
            // TODO: 22.08.2023 log exception
            e.printStackTrace();
        }
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}