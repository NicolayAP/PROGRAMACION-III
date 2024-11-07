package co.edu.uptc.view;

import java.util.Scanner;

public class View {
    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public float readFloat(String message) {
        showMessage(message);
        return sc.nextFloat();
    }

    public int readInt(String message) {
        showMessage(message);
        return sc.nextInt();
    }
}
