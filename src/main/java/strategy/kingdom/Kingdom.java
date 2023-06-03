package strategy.kingdom;

import strategy.location.castle.Castle;

public interface Kingdom extends Runnable {
    void terminate();
    void attack(Kingdom kingdom);
    boolean canFight();
    Castle getCastle();
}
