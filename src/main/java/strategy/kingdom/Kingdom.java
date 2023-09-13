package strategy.kingdom;

import strategy.military.infantry.InfantryUnit;
import strategy.location.castle.Castle;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;
import strategy.util.Time;

import java.util.Collection;

public interface Kingdom extends Runnable, MessagesSender<JSONMessage> {
    void terminate();
    void attack(Kingdom kingdom);
    Castle getCastle();
    void addInfantryUnits(Collection<InfantryUnit> infantryUnits);
	Time getAttackTime();
}
