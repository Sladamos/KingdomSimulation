package strategy.simulation;

import strategy.kingdom.KingdomConfig;

public interface ManualSimulationConfig extends SimulationConfig {
    KingdomConfig getFirstKingdomConfig();
    KingdomConfig getSecondKingdomConfig();
}
