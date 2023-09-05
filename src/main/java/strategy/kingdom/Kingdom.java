package strategy.kingdom;

import strategy.location.castle.Castle;
import strategy.military.infantry.InfantryUnit;

import java.util.Collection;

public interface Kingdom extends Runnable {
    void terminate();
    void attack(Kingdom kingdom);
    Castle getCastle();
    void addInfantry(Collection<InfantryUnit> infantryUnits);
	long getAttackTime();
}
