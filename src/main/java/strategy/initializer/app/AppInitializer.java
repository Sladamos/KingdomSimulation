package strategy.initializer.app;

import strategy.initializer.SimulationInitializer;

public interface AppInitializer {
    SimulationInitializer createSimulationInitializer();
}
