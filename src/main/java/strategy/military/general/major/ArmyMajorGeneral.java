package strategy.military.general.major;

import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;

public interface ArmyMajorGeneral extends Runnable, Fightable {
    void addUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits);
    void terminate();
}
