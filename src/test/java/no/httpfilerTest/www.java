package no.httpfilerTest;

import java.io.IOException;
import java.net.Socket;

public class www {

    private final String host;
    private String requestTarget;

    public www(String host, String requestTarget) {
        this.host = host;
        this.requestTarget = requestTarget;
    }

    public static void main(String[] args) throws IOException {
        new www("urlecho.appspot.com", "/echo?status=200&Content-Type=text%2Fhtml&body=Hello%20world!").executeRequest();
    }

    public WwwClientResponse executeRequest() throws IOException {
        try (Socket socket = new Socket(host, 80)) {
            socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n").getBytes());
            socket.getOutputStream().write(("Host:" + host + "\r\n").getBytes());
            socket.getOutputStream().write("Connection: close\r\n".getBytes());
            socket.getOutputStream().write("\r\n".getBytes());
            socket.getOutputStream().flush();

            WwwClientResponse wwwClientResponse = new WwwClientResponse(socket);
            wwwClientResponse.invoke();
            return wwwClientResponse;
        }
    }

}