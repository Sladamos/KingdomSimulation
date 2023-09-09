package strategy.simulation;

import strategy.kingdom.KingdomConfig;
import util.Time;

public interface AutomaticSimulationConfig extends SimulationConfig {
    KingdomConfig getFirstKingdomConfig();
    KingdomConfig getSecondKingdomConfig();
    Time getFirstKingdomDevelopmentTime();
    Time getSecondKingdomDevelopmentTime();
}
