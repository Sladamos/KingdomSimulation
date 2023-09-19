package strategy.initializer.simulation;

import strategy.app.App;
import strategy.battle.creator.BattleCreator;
import strategy.config.simulation.ManualSimulationConfigParser;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.json.JSON;
import strategy.simulation.ManualSimulationConfig;
import strategy.simulation.executioner.SimulationExecutioner;

public class ManualSimulationInitializer implements SimulationInitializer {

    private final BattleCreator battleCreator;

    private final ManualSimulationConfig simulationConfig;

    public ManualSimulationInitializer(BattleCreator battleCreator) {
        this.battleCreator = battleCreator;
        simulationConfig = createSimulationConfig();
    }

    private ManualSimulationConfig createSimulationConfig() {
        ManualSimulationConfigParser configParser = new ManualSimulationConfigParser();
        FileJsonLoader loader = new FileJsonLoaderImpl();
        loader.setFileName("config.json");
        JSON json = loader.loadJson();
        return configParser.createConfig(json);
    }

    @Override
    public void initializeSimulation(App app, SimulationExecutioner simulationExecutioner) {
    }
}
