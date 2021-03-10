package net.jeremycastel.anotherfano;

/**
 * Singleton server.
 * 
 * Stores users and fanos.
 */
public class Server {
    private static Server instance = null;

    private Server() { };

    public static Server getInstance() {
        if (Server.instance == null) {
            Server.instance = new Server();
        }

        return Server.instance;
    }
}