package strategy;

import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;

public class Simulation {

    public static void main(String[] args) {
        Kingdom kingdom = new SarraxKingdom();
        kingdom.run();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        kingdom.terminate();
        System.out.println("łazap");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
    }
}
