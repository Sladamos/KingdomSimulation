package strategy;

import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;

public class Simulation {

    public static void main(String[] args) {
        Kingdom kingdom = new SarraxKingdom();
        kingdom.run();
    }
}
