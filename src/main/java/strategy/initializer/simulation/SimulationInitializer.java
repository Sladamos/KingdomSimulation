package strategy.initializer.simulation;

import strategy.app.App;
import strategy.simulation.api.SimulationAPI;

public interface SimulationInitializer {
    void initializeSimulation(App app, SimulationAPI simulationAPI);
}
