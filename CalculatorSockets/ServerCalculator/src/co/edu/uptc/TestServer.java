package co.edu.uptc;

import co.edu.uptc.controllerServer.ControllerServer;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ControllerServer controllerServer = new ControllerServer();
        controllerServer.start();
    }
}
