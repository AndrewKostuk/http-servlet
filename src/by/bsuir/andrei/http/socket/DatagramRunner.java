package by.bsuir.andrei.http.socket;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {
        try (var socket = new DatagramSocket()) {
            byte[] buffer = "UDP socket".getBytes();
            var localhost = Inet4Address.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, localhost, 7777);
            socket.send(packet);
        }
    }
}
