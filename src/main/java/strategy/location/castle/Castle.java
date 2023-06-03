package strategy.location.castle;

import strategy.location.Location;
import strategy.military.infantry.InfantryUnit;

import java.util.Collection;

public interface Castle extends Location {
    void attack(Castle castle);
    void receiveDamage(Collection<Integer> damage);
    void addInfantry(Collection<InfantryUnit> infantryUnits);
}
