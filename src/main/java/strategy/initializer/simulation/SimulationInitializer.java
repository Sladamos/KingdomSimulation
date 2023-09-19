package strategy.initializer.simulation;

import strategy.app.App;
import strategy.simulation.executioner.SimulationExecutioner;

public interface SimulationInitializer {
    void initializeSimulation(App app, SimulationExecutioner simulationExecutioner);
}
