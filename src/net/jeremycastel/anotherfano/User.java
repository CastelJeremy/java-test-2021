package net.jeremycastel.anotherfano;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import net.jeremycastel.anotherfano.fanos.Fano;

/**
 * A user has a unique name.
 * 
 * A user can subscribe to a fano and will be notified if it ends a lap.
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void notifyLap(Fano fano) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println(fano.getNumber() + " at " + now.format(formatter));
    }
}
