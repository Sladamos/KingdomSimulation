package strategy;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;

public class Simulation {

    public static void main(String[] args) {
        Kingdom strongerKingdom = createKingdom(5000);
        Kingdom weakerKingdom = createKingdom(2500);
        Battle battle = new SimpleBattle(strongerKingdom, weakerKingdom);
        Thread thread = new Thread(battle);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
