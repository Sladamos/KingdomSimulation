package strategy;

import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;

public class Simulation {

    public static void main(String[] args) {
        Kingdom strongerKingdom = createKingdom(50000);
        Kingdom weakerKingdom = createKingdom(25000);
        //strongerKingdom.attack(weakerKingdom)
        strongerKingdom.terminate();
        weakerKingdom.terminate();
    }

    private static Kingdom createKingdom(long sleep) {
        Kingdom kingdom = new SarraxKingdom();
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        return kingdom;
    }
}
