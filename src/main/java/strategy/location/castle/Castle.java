package strategy.location.castle;

import strategy.location.Location;
import strategy.item.military.infantry.InfantryUnit;

import java.util.Collection;

public interface Castle<T extends InfantryUnit> extends Location {
    void attack(Castle<?> castle);
    void receiveDamage(Collection<Integer> damage);
    void addInfantry(Collection<T> infantryUnits);
}
