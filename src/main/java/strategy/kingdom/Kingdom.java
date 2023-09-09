package strategy.kingdom;

import strategy.item.military.infantry.warrior.Warrior;
import strategy.location.castle.Castle;
import util.Time;

import java.util.Collection;

public interface Kingdom extends Runnable {
    void terminate();
    void attack(Kingdom kingdom);
    Castle getCastle();
    void addWarriors(Collection<Warrior> warriors);
	Time getAttackTime();
}
