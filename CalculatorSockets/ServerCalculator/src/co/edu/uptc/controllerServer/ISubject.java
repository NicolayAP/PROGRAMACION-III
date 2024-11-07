package co.edu.uptc.controllerServer;

import java.util.Observer;

public interface ISubject {
    public void attach(IObserver observer);
    public void detach(IObserver observer);
    public void notifyObservers();
}
