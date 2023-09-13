package strategy.military.general;

import strategy.military.MilitaryUnit;
import strategy.military.army.Army;
import strategy.item.statistic.Happiness;

import java.util.Collection;
import java.util.function.Consumer;

public interface General extends Consumer<Happiness> {
    void runHappinessConusmer();
    void runUnitsConusmer();
    void terminate();
    void accept(MilitaryUnit militaryUnit);
    void addMilitaryUnits(Collection<MilitaryUnit> militaryUnits);
    Army getArmy();
}
