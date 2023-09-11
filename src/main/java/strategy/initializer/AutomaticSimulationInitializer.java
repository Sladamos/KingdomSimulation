package strategy.initializer;

import strategy.app.AppCommunicator;
import strategy.battle.Battle;
import strategy.battle.operator.BattleOperator;
import strategy.config.simulation.AutomaticSimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoader;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.simulation.AutomaticSimulationConfig;

public class AutomaticSimulationInitializer implements SimulationInitializer {

    private final BattleOperator battleOperator;

    public AutomaticSimulationInitializer(BattleOperator battleOperator) {
        this.battleOperator = battleOperator;
    }

    @Override
    public void initializeSimulation(AppCommunicator appCommunicator) {
        AutomaticSimulationConfig simulationConfig = createSimulationConfig();
        KingdomInitializer kingdomInitializer = new KingdomInitializer();
        Kingdom firstKingdom = kingdomInitializer.createKingdom(simulationConfig.getFirstKingdomDevelopmentTime().getMiliseconds(),
                simulationConfig.getFirstKingdomConfig());
        //appCommunicator.bindKingdomSender();
        Kingdom secondKingdom = kingdomInitializer.createKingdom(simulationConfig.getSecondKingdomDevelopmentTime().getMiliseconds(),
                simulationConfig.getSecondKingdomConfig());

        Battle battle = battleOperator.createBattle(firstKingdom, secondKingdom);
        appCommunicator.bindBattleSender(battle);
        firstKingdom.terminate();
        secondKingdom.terminate();
        battleOperator.simulateBattle(battle);
    }

    private AutomaticSimulationConfig createSimulationConfig() {
        AutomaticSimulationConfigParser configParser = new AutomaticSimulationConfigParser();
        JsonLoader loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");
        return configParser.createConfig(json);
    }
}
