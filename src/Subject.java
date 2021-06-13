import java.util.ArrayList;

public abstract class Subject {
    public Subject(ArrayList<Observer> observers){
        this.observers=observers;
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
    public void notify1(){
        //notify methodu için Object override hatası veriyor
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this);
        }
    }
    protected ArrayList<Observer> observers = new ArrayList<Observer>();
}
