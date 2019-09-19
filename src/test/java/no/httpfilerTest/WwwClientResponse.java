package no.httpfilerTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class WwwClientResponse {
    private Socket socket;
    private String statusLine;

    public WwwClientResponse(Socket socket) {
        this.socket = socket;
    }

    public void invoke() throws IOException {
        InputStream input = socket.getInputStream();
        int c; // c is really am byte (0-255), but har to be a int for -1
        StringBuilder statusLine = new StringBuilder();
        while ((c = input.read()) != -1 && c != '\r'){
            statusLine.append((char)c);
        }
        this.statusLine = statusLine.toString();

        while ((c = input.read()) != -1) { // -1 means "end of stream"
            System.out.print((char) c); // Force c (which is an int) to be interpreted as a character
        }
    }

    public int getStatusCode() {
        return Integer.parseInt(statusLine.split(" ")[1]);
    }
}
