package strategy.initializer.app;

import strategy.app.AppConfig;
import strategy.battle.operator.BattleOperatorCreator;
import strategy.config.AppConfigParser;
import strategy.error.CriticalAppError;
import strategy.initializer.simulation.AutomaticSimulationInitializer;
import strategy.initializer.simulation.SimulationInitializer;
import strategy.json.JSON;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.simulation.SimulationType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AppInitializerFromFile implements AppInitializer {
    
    private final Map<SimulationType, Supplier<SimulationInitializer>> simulationInitializer;

    public AppInitializerFromFile(BattleOperatorCreator battleOperatorCreator) {
        simulationInitializer = new HashMap<>();
        simulationInitializer.put(SimulationType.AUTOMATIC, () -> new AutomaticSimulationInitializer(battleOperatorCreator.createBasicBattleOperator()));
    }

    @Override
    public SimulationInitializer createSimulationInitializer() {
        AppConfig appConfig = createAppConfig();
        SimulationType simulationType = appConfig.getSimulationType();
        if(!simulationInitializer.containsKey(simulationType))
            throw new CriticalAppError("Incorrect simulation type in app config file.");
        return simulationInitializer.get(simulationType).get();
    }

    private AppConfig createAppConfig() {
        AppConfigParser configParser = new AppConfigParser();
        FileJsonLoader loader = new FileJsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("app.json");
        return configParser.createConfig(json);
    }
}
