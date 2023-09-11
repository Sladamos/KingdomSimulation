package strategy.initializer;

import strategy.app.AppCommunicator;

public interface SimulationInitializer {
    void initializeSimulation(AppCommunicator appCommunicator);
}
