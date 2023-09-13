package strategy.military.general.major;

import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;

import java.util.Collection;

public interface ArmyMajorGeneral extends Runnable {
    void addUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits);
    void terminate();
}
