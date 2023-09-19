package strategy.initializer.simulation;

import strategy.app.App;
import strategy.battle.Battle;
import strategy.battle.creator.BattleCreator;
import strategy.config.simulation.ManualSimulationConfigParser;
import strategy.error.BasicAppError;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.json.JSON;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.ManualSimulationConfig;
import strategy.simulation.executioner.SimulationExecutioner;
import strategy.util.TimeImpl;

import java.util.HashMap;
import java.util.Map;

public class ManualSimulationInitializer implements SimulationInitializer {

    private final BattleCreator battleCreator;

    private final ManualSimulationConfig simulationConfig;

    private final Map<String, Kingdom> kingdomMap;

    private final Map<Integer, Battle> battleMap;

    private App app;

    private SimulationExecutioner simulationExecutioner;

    public ManualSimulationInitializer(BattleCreator battleCreator) {
        this.battleCreator = battleCreator;
        simulationConfig = createSimulationConfig();
        kingdomMap = new HashMap<>();
        battleMap = new HashMap<>();
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
        this.app = app;
        this.simulationExecutioner = simulationExecutioner;

        createKingdom(simulationConfig.getFirstKingdomConfig());
        createKingdom(simulationConfig.getSecondKingdomConfig());
        app.onKingdomLaunched(this::onKingdomLaunched);
        app.onKingdomStopped(this::onKingdomStopped);
        app.onBattleLaunched(this::onBattleLaunched);
        app.onBattleStopped(this::onBattleStopped);
        app.enableInputHandling();
        simulationExecutioner.waitForKingdomsDevelopingEnd();
        simulationExecutioner.waitForBattlesEnd();

        this.app = null;
        this.simulationExecutioner = null;
    }

    private void onBattleStopped(Integer battleId) {
        if (!battleMap.containsKey(battleId)) {
            throw new BasicAppError("Incorrect battle id");
        }
        Battle battle = battleMap.get(battleId);
        simulationExecutioner.stopBattle(battle);
    }

    private void onBattleLaunched(BattleConfig battleConfig) {
        Integer battleId = battleConfig.getBattleId();
        String firstKingdomId = battleConfig.getFirstKingdomId();
        String secondKingdomId = battleConfig.getSecondKingdomId();

        if (battleMap.containsKey(battleId)) {
            throw new BasicAppError("Incorrect battle id");
        }
        if (!kingdomMap.containsKey(firstKingdomId)) {
            throw new BasicAppError("Incorrect first kingdom id");
        }
        if (!kingdomMap.containsKey(secondKingdomId)) {
            throw new BasicAppError("Incorrect second kingdom id");
        }

        Kingdom firstKingdom = kingdomMap.get(firstKingdomId);
        Kingdom secondKingdom = kingdomMap.get(secondKingdomId);
        Battle battle = battleCreator.createBattle(firstKingdom, secondKingdom);
        simulationExecutioner.launchBattle(battle);
        battleMap.put(battleId, battle);
    }


    private void onKingdomLaunched(String kingdomId) {
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.launchKingdomDeveloping(kingdom, new TimeImpl(Integer.MAX_VALUE));
    }

    private void onKingdomStopped(String kingdomId) {
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.stopKingdom(kingdom);
    }

    private void createKingdom(KingdomConfig kingdomConfig) {
        Kingdom kingdom = simulationExecutioner.createKingdom(kingdomConfig);
        kingdomMap.put(kingdom.getId(), kingdom);
    }
}
