package co.edu.uptc.model;

public class Calculator {
    private float memory = 0;

    public float add(float a, float b) {
        return a + b;
    }
    public float sub(float a, float b) {
        return a - b;
    }
    public float mul(float a, float b) {
        return a * b;
    }
    public float div(float a, float b) {
        if(b==0){
            return 0;
        }
        return a / b;
    }
    public synchronized float updateMemory(float result) {
        memory = memory + result;
        return memory;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }
}
