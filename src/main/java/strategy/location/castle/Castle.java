package strategy.location.castle;

import strategy.location.Location;

import java.util.Collection;

public interface Castle extends Location {
    void attack(Castle castle);
    void receiveDamage(Collection<Integer> damage);
}
