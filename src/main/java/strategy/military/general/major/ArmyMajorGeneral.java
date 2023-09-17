package strategy.military.general.major;

import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;

public interface ArmyMajorGeneral extends Runnable, Fightable, MessagesNotifier<JSONMessage> {
    void addUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits);
    void terminate();
}
