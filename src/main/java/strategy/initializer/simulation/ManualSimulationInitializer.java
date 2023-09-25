package strategy.initializer.simulation;

import strategy.app.App;
import strategy.battle.Battle;
import strategy.battle.BattleConfig;
import strategy.battle.creator.BattleCreator;
import strategy.config.simulation.ManualSimulationConfigParser;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.json.JSON;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.ManualSimulationConfig;
import strategy.simulation.api.SimulationAPI;

public class ManualSimulationInitializer implements SimulationInitializer {

    private final BattleCreator battleCreator;

    private final ManualSimulationConfig simulationConfig;

    private SimulationAPI simulationAPI;

    private App app;

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
    public void initializeSimulation(App app, SimulationAPI simulationAPI) {
        this.app = app;
        this.simulationAPI = simulationAPI;
        createKingdom(simulationConfig.getFirstKingdomConfig());
        createKingdom(simulationConfig.getSecondKingdomConfig());
        app.addKingdomLaunchedListener(this::onKingdomLaunched);
        app.addKingdomStoppedListener(this::onKingdomStopped);
        app.addBattleLaunchedListener(this::onBattleLaunched);
        app.addBattleStoppedListener(this::onBattleStopped);
        app.enableExecutingOptions();
        app.waitForAppClose();
        simulationAPI.stopSimulation();
    }

    private synchronized void onBattleStopped(Integer battleId) {
        simulationAPI.stopBattle(battleId);
    }

    private synchronized void onBattleLaunched(BattleConfig battleConfig) {
        Battle battle = simulationAPI.launchBattle(battleCreator, battleConfig);
        app.bindBattleSender(battle);
    }

    private synchronized void onKingdomLaunched(String kingdomId) {
        simulationAPI.launchKingdom(kingdomId);
    }

    private synchronized void onKingdomStopped(String kingdomId) {
        simulationAPI.stopKingdom(kingdomId);
    }

    private void createKingdom(KingdomConfig kingdomConfig) {
        Kingdom kingdom = simulationAPI.createKingdom(kingdomConfig);
        app.bindKingdomSender(kingdom);
    }
}
