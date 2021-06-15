import java.util.ArrayList;

public abstract class Subject {
    protected ArrayList<Observer> observers;

    public Subject(){
        this.observers = new ArrayList<>();
    }

    void addObserver(Observer observer){
        observers.add(observer);
    }

    void removeObserver(Observer observer){
        for (int i = 0; i< observers.size(); i++) {
            if (observers.get(i) == observer) {
                observers.remove(i);
                return;
            }
        }
    }

    public abstract void notifyObservers();
}
