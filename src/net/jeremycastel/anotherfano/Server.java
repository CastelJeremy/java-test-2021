package net.jeremycastel.anotherfano;

import java.util.ArrayList;

import net.jeremycastel.anotherfano.fanos.Fano;

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
}