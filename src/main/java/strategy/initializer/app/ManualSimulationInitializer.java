package strategy.initializer.app;

import strategy.app.AppCommunicator;
import strategy.battle.operator.BattleOperator;
import strategy.initializer.simulation.SimulationInitializer;
import strategy.simulation.executioner.SimulationExecutioner;

public class ManualSimulationInitializer implements SimulationInitializer {
    public ManualSimulationInitializer(BattleOperator basicBattleOperator) {
    }

    @Override
    public void initializeSimulation(AppCommunicator appCommunicator, SimulationExecutioner simulationExecutioner) {

    }
}
