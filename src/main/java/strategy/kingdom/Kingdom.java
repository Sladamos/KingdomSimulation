package strategy.kingdom;

import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;
import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;
import strategy.military.mechanism.fight.Fightable;
import strategy.util.Time;

import java.util.Collection;

public interface Kingdom extends Runnable, MessagesSender<JSONMessage>, Fightable {
    void terminate();
    void addMilitaryUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits);
	Time getAttackTime();
    String getId();
}
