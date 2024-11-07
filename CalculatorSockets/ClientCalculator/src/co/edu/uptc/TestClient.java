package co.edu.uptc;



import co.edu.uptc.controllerClient.ControllerClient;

import java.io.IOException;

public class TestClient {
    public static void main(String[] args) throws IOException{
        ControllerClient client = new ControllerClient();
        client.start();
    }
}
