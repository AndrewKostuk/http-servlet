package by.bsuir.andrei.http.server;

public class ServerRunner {

    public static void main(String[] args) {
        var server = new HttpServer(9000, 100);
        server.run();
    }
}
