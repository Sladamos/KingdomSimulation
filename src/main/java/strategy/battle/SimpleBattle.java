package strategy.battle;

import strategy.kingdom.Kingdom;
import strategy.military.ArmyDestroyedException;

public class SimpleBattle implements Battle {

    private final Kingdom firstKingdom;

    private final Kingdom secondKingdom;

    private boolean areFighting;

    public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        this.firstKingdom = firstKingdom;
        this.secondKingdom = secondKingdom;
        areFighting = false;
    }

    @Override
    public void run() {
        areFighting = true;
        while(areFighting) {
            firstAttack();
            if(areFighting) {
                secondAttack();
            }
        }
    }

    private void firstAttack() {
        try {
            Thread.sleep(5000);
            System.out.println("First kingdom attacked");
            firstKingdom.attack(secondKingdom);
        } catch (ArmyDestroyedException ignored) {
            System.out.println("First kingdom won the battle");
            areFighting = false;
        } catch (Exception ignored) {
            areFighting = false;
        }

    }

    private void secondAttack() {
        try {
            Thread.sleep(5000);
            System.out.println("Second kingdom attacked");
            secondKingdom.attack(firstKingdom);
        } catch (ArmyDestroyedException ignored) {
            System.out.println("Second kingdom won the battle");
            areFighting = false;
        } catch (Exception ignored) {
            areFighting = false;
        }
    }

}
