package strategy.location.castle;

import strategy.location.Location;

public interface Castle extends Location {
    void attack(Castle castle);
}
