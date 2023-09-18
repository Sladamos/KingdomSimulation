package strategy.initializer.simulation;

import strategy.app.AppCommunicator;
import strategy.simulation.executioner.SimulationExecutioner;

public interface SimulationInitializer {
    void initializeSimulation(AppCommunicator appCommunicator, SimulationExecutioner simulationExecutioner);
}
