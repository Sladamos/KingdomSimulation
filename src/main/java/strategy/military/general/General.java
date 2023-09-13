package strategy.military.general;

import strategy.military.MilitaryUnit;
import strategy.military.army.Army;
import strategy.item.statistic.Happiness;

import java.util.Collection;
import java.util.function.Consumer;

public interface General {
    void runHappinessConusmer();
    void runUnitsConusmer();
    void terminate();
    void accept(MilitaryUnit militaryUnit);
    void accept(Happiness happiness);
    void addMilitaryUnits(Collection<MilitaryUnit> militaryUnits);
    Army getArmy();
}
