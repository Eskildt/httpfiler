package no.httpfilerTest;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class wwwTest {

    @Test
    void mathShouldWork(){
        assertEquals(4, 2+2);
    }


    @Test
    void shouldReadStatusCode() throws IOException {
        www client = new www ("urlecho.appspot.com", "/echo");
        WwwClientResponse response = client.executeRequest();
        assertEquals(200, response.getStatusCode());
    }
    @Test
    void shouldReadFailureStatusCode() throws IOException {
        www client = new www ("urlecho.appspot.com", "/echo?status=401");
        WwwClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());
    }
}