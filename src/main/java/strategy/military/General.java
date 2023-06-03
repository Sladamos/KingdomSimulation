package strategy.military;

import strategy.product.statistic.Happiness;

import java.util.Collection;
import java.util.function.Consumer;

public interface General extends Consumer<Happiness> {
    Collection<Integer> getArmyDamage();
    void receiveDamage(Collection<Integer> damages);
    void runHappinessConusmer();
    void runUnitsConusmer();
    void terminate();
}
