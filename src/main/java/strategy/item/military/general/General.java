package strategy.item.military.general;

import strategy.item.military.MilitaryUnit;
import strategy.item.military.army.Army;
import strategy.item.military.infantry.InfantryUnit;
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
