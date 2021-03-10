package net.jeremycastel.anotherfano;

import java.util.ArrayList;

import javax.swing.Timer;

import net.jeremycastel.anotherfano.fanos.Fano;
import net.jeremycastel.anotherfano.fanos.FanoFactory;
import net.jeremycastel.anotherfano.fanos.Vehicule;

/**
 * Singleton server.
 * 
 * Stores users and fanos.
 */
public class Server {
    private static Server instance = null;

    private ArrayList<Fano> fanos;
    private ArrayList<User> users;

    private Server() {
        this.fanos = new ArrayList<>();
        this.users = new ArrayList<>();
    };

    public static Server getInstance() {
        if (Server.instance == null) {
            Server.instance = new Server();
        }

        return Server.instance;
    }

    public boolean addFano(Fano fano) throws Exception{
        for(Fano tmpFano : this.fanos) {
            if (fano.getNumber() == tmpFano.getNumber()) {
                throw new Exception("This fano number already exists");
            }
        }

        return this.fanos.add(fano);
    }

    public Fano getFano(int number) {
        for (Fano fano : this.fanos) {
            if (fano.getNumber() == number)
                return fano;
        }

        return null;
    }

    public boolean removeFano(Fano fano) {
        return this.fanos.remove(fano);
    }

    public boolean addUser(User user) throws Exception {
        for (User tmpUser : this.users) {
            if (user.getName().equals(tmpUser.getName())) {
                throw new Exception("This user name already exists");
            }
        }

        return this.users.add(user);
    }

    public User getUser(String name) {
        for (User user : this.users) {
            if (user.getName().equals(name))
                return user;
        }

        return null;
    }

    public boolean removeUser(User user) {
        for (Fano fano : this.fanos) {
            fano.removeSubscriber(user);
        }
        
        return this.users.remove(user);
    }

    public static void main(String [] args) {
        System.out.println("AnotherFano - V1.0");

        Fano fano1 = FanoFactory.create(Vehicule.CAR, 46, 1);
        Fano fano2 = FanoFactory.create(Vehicule.KART, 10, 2);
        User user1 = new User("premier");
        User user2 = new User("second");

        /**
         * Try and catch in case we create a fano with a number already used or
         * a user with a name already used.
         */
        try {
            Server.getInstance().addFano(fano1);
            Server.getInstance().addFano(fano2);
            Server.getInstance().addUser(user1);
            Server.getInstance().addUser(user2);

            // We could use fano1, user1... but we want to demonstrate server usage.
            Server.getInstance().getFano(46).addSubscriber(Server.getInstance().getUser("premier"));
            Server.getInstance().getFano(10).addSubscriber(Server.getInstance().getUser("second"));
            Server.getInstance().getFano(46).addSubscriber(Server.getInstance().getUser("second"));

            // Lap time is in seconds, must be converted to milliseconds
            Timer timer1 = new Timer(fano1.getLapTime() * 1000, evt -> fano1.notifySubscribers());
            Timer timer2 = new Timer(fano2.getLapTime() * 1000, evt -> fano2.notifySubscribers());
            timer1.start();
            timer2.start();

            Thread.sleep(10000);

            timer1.stop();
            timer2.stop();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}