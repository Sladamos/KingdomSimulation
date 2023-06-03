package strategy.battle;

import strategy.kingdom.Kingdom;

public class SimpleBattle implements Battle {

    private final Kingdom firstKingdom;

    private final Kingdom secondKingdom;

    public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        this.firstKingdom = firstKingdom;
        this.secondKingdom = secondKingdom;
    }

    @Override
    public void run() {
        while(true) {
            firstKingdom.attack(secondKingdom);
            if(!secondKingdom.canFight()) {
                System.out.println("First kingdom won the battle");
                break;
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                break;
            }
            secondKingdom.attack(firstKingdom);
            if(!firstKingdom.canFight()) {
                System.out.println("Second kingdom won the battle");
                break;
            }
        }
    }

}
