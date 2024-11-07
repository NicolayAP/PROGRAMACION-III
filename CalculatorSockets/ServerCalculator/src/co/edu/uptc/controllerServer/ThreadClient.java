package co.edu.uptc.controllerServer;

import co.edu.uptc.model.Calculator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadClient extends Thread implements IObserver {
    private Socket socket;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private ControllerServer server;

    public ThreadClient(Socket socket, ControllerServer server) {
        this.socket = socket;
        this.server = server;
    }

    public float calculateNumbers(int opc, float a, float b) {
        Calculator calculator = server.getCalculator();
        float result = 0;
        switch (opc) {
            case 1:
                result = calculator.add(a, b);
                break;
            case 2:
                result = calculator.sub(a, b);
                break;
            case 3:
                result = calculator.mul(a, b);
                break;
            case 4:
                result = calculator.div(a, b);
                break;
        }
        return result;
    }

    public String menu(int opc, float resultCalculator) throws IOException {
        switch (opc) {
            case 1: return "Resultado suma: " + resultCalculator;
            case 2: return "Resultado resta: " + resultCalculator;
            case 3: return "Resultado multiplicacion: " + resultCalculator;
            case 4: return "Resultado division: " + resultCalculator;
            case 5: return "Saliendo...";
            default: return "dato invalido";
        }
    }

    public void run() {
        try {
            int opc;
            do {
                salida = new DataOutputStream(socket.getOutputStream());
                entrada = new DataInputStream(socket.getInputStream());
                opc = entrada.readInt();
                if (opc >= 1 && opc <= 4) {
                    float a = entrada.readFloat();
                    float b = entrada.readFloat();
                    float result = calculateNumbers(opc, a, b);
                    salida.writeUTF(menu(opc, result));
                    salida.writeUTF("Memoria global: " + server.updateMemory(result));
                }
            } while (opc != 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            salida.writeUTF("Memoria global actualizada: " + server.getCalculator().getMemory());
        } catch (IOException e) {
            System.err.println("Error al enviar actualización de memoria al cliente: " + e.getMessage());
            try {
                socket.close();
                server.detach(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

