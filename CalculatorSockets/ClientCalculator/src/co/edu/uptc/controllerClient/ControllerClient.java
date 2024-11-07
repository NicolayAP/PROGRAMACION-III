package co.edu.uptc.controllerClient;

import co.edu.uptc.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ControllerClient {
    private final int PUERTO = 1234;
    private final String HOST = "localhost";
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private View view;

    public ControllerClient() throws IOException {
        socket = new Socket(HOST, PUERTO);
        salida = new DataOutputStream(socket.getOutputStream());
        entrada = new DataInputStream(socket.getInputStream());
        view = new View();
        startListenerThread();
    }

    private void startListenerThread() {
        Thread listener = new Thread(() -> {
            try {
                while (true) {
                    String message = entrada.readUTF();
                    view.showMessage(message);
                }
            } catch (IOException e) {
                System.err.println("Error en la conexión de escucha: " + e.getMessage());
            }
        });
        listener.start();
    }

    public void start() throws IOException {
        try {
            int opc;
            view.showMessage("Bienvenido a la calculadora");
            do {
                opc = view.readInt("CALCULADORA \nIngrese una opcion: \n1.Sumar \n2.Restar \n3.Multiplicacion \n4.Divicion \n5.Salir ");
                salida.writeInt(opc);
                if (opc >= 1 && opc <= 4) {
                    float a = view.readFloat("Ingrese el primer numero");
                    float b = view.readFloat("Ingrese el segundo numero");
                    salida.writeFloat(a);
                    salida.writeFloat(b);
                }
            } while (opc != 5);
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        } finally {
            socket.close();
        }
    }

}
