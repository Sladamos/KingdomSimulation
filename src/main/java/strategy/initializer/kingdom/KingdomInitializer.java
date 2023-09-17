package strategy.initializer.kingdom;

import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;

public interface KingdomInitializer {
    Kingdom createKingdom(KingdomConfig kingdomConfig);
}
