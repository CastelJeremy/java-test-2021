package net.jeremycastel.anotherfano.fanos;

import java.util.ArrayList;
import net.jeremycastel.anotherfano.User;

/**
 * A fano is a vehicule with a number, a lapTime and a list of subscribers.
 * 
 * When it ends a lap, every subsribers are notified.
 */
public class Fano {
    private int number;
    private double lapTime;
    private ArrayList<User> subscribers;

    public Fano(int number, double lapTime) {
        this.number = number;
        this.lapTime = lapTime;
        this.subscribers = new ArrayList<>();
    }

    public int getNumber() {
        return this.number;
    }

    public void setLapTime(double lapTime) {
        this.lapTime = lapTime;
    }

    public double getLapTime() {
        return this.lapTime;
    }

    public boolean addSubscriber(User user) {
        return this.subscribers.add(user);
    }

    // Notify each observer
    public void notifySubscribers() {
        for (User user : this.subscribers) {
            user.notifyLap(this);
        }
    }

    public boolean removeSubscriber(User user) {
        return this.subscribers.remove(user);
    }
}
