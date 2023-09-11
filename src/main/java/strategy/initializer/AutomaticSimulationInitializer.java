package strategy.initializer;

import strategy.app.AppCommunicator;
import strategy.battle.Battle;
import strategy.battle.operator.BattleOperator;
import strategy.config.simulation.AutomaticSimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoader;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.operator.KingdomOperator;
import strategy.kingdom.operator.KingdomOperatorImpl;
import strategy.simulation.AutomaticSimulationConfig;

public class AutomaticSimulationInitializer implements SimulationInitializer {

    private final BattleOperator battleOperator;

    private final KingdomOperator kingdomOperator;

    private AppCommunicator appCommunicator;

    public AutomaticSimulationInitializer(BattleOperator battleOperator) {
        this.battleOperator = battleOperator;
        this.kingdomOperator = new KingdomOperatorImpl();
    }

    @Override
    public void initializeSimulation(AppCommunicator appCommunicator) {
        this.appCommunicator = appCommunicator;
        AutomaticSimulationConfig simulationConfig = createSimulationConfig();
        Kingdom firstKingdom = createKingdom(simulationConfig.getFirstKingdomConfig());
        Kingdom secondKingdom = createKingdom(simulationConfig.getSecondKingdomConfig());
        kingdomOperator.launchKingdom(firstKingdom, simulationConfig.getFirstKingdomDevelopmentTime().getMiliseconds());
        kingdomOperator.launchKingdom(secondKingdom, simulationConfig.getSecondKingdomDevelopmentTime().getMiliseconds());
        kingdomOperator.waitForDevelopingEnd();

        Battle battle = battleOperator.createBattle(firstKingdom, secondKingdom);
        simulateBattle(battle);
        this.appCommunicator = null;
    }

    private AutomaticSimulationConfig createSimulationConfig() {
        AutomaticSimulationConfigParser configParser = new AutomaticSimulationConfigParser();
        JsonLoader loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");
        return configParser.createConfig(json);
    }

    private Kingdom createKingdom(KingdomConfig kingdomConfig) {
        Kingdom kingdom = kingdomOperator.createKingdom(kingdomConfig);
        appCommunicator.bindKingdomSender(kingdom);
        return kingdom;
    }

    private void simulateBattle(Battle battle) {
        appCommunicator.bindBattleSender(battle);
        battleOperator.simulateBattle(battle);
    }
}