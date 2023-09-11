package strategy.initializer.app;

import strategy.initializer.simulation.SimulationInitializer;

public interface AppInitializer {
    SimulationInitializer createSimulationInitializer();
}
