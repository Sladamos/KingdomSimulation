package strategy.military;

import java.util.Collection;

public interface General {
    Collection<Integer> getArmyDamage();
    void receiveDamage(Collection<Integer> damages);
}
