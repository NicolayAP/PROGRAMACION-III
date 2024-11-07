package co.edu.uptc.controllerServer;

import co.edu.uptc.model.Calculator;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ControllerServer implements ISubject {
    private final int PUERTO = 1234;
    private ServerSocket serverSocket;
    private ArrayList<IObserver> clients;
    private Calculator calculator;

    public ControllerServer() throws IOException {
        serverSocket = new ServerSocket(PUERTO);
        clients = new ArrayList<>();
        calculator = new Calculator();
    }

    public void start() throws IOException {
        while (true) {
            ThreadClient threadClient = new ThreadClient(serverSocket.accept(), this);
            threadClient.start();
            attach(threadClient);
        }
    }

    public synchronized float updateMemory(float result) {
        float updatedMemory = calculator.updateMemory(result);
        notifyObservers();
        return updatedMemory;
    }

    @Override
    public void attach(IObserver observer) {
        clients.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        clients.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver client : clients) {
            client.update();
        }
    }

    public Calculator getCalculator() {
        return calculator;
    }
}
