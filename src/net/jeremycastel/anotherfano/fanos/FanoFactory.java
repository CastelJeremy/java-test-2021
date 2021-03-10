package net.jeremycastel.anotherfano.fanos;

/**
 * Creates a fano with the specified vehicule type.
 */
public class FanoFactory {
    public static Fano create(Vehicule vehicule, int number, double lapTime) {
        switch(vehicule) {
            case BIKE:
                return new Bike(number, lapTime);
            case CAR:
                return new Car(number, lapTime);
            case KART:
                return new Kart(number, lapTime);
            default:
                return null;
        }
    }
}
