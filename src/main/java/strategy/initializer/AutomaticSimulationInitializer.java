package strategy.initializer;

import strategy.battle.BattleSimulator;
import strategy.config.simulation.AutomaticSimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoader;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.simulation.AutomaticSimulationConfig;

public class AutomaticSimulationInitializer implements SimulationInitializer {

    private final BattleSimulator battleSimulator;

    public AutomaticSimulationInitializer(BattleSimulator battleSimulator) {
        this.battleSimulator = battleSimulator;
    }

    @Override
    public void initializeSimulation() {
        AutomaticSimulationConfig simulationConfig = createSimulationConfig();
        KingdomInitializer kingdomInitializer = new KingdomInitializer();
        Kingdom firstKingdom = kingdomInitializer.createKingdom(simulationConfig.getFirstKingdomDevelopmentTime().getMiliseconds(),
                simulationConfig.getFirstKingdomConfig());

        Kingdom weakerKingdom = kingdomInitializer.createKingdom(simulationConfig.getSecondKingdomDevelopmentTime().getMiliseconds(),
                simulationConfig.getSecondKingdomConfig());

        firstKingdom.terminate();
        weakerKingdom.terminate();
        battleSimulator.simulateBattle(firstKingdom, weakerKingdom);
    }

    private AutomaticSimulationConfig createSimulationConfig() {
        AutomaticSimulationConfigParser configParser = new AutomaticSimulationConfigParser();
        JsonLoader loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");
        return configParser.createConfig(json);
    }
}
