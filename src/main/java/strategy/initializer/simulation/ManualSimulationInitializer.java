package strategy.initializer.simulation;

import strategy.app.App;
import strategy.battle.Battle;
import strategy.battle.BattleConfig;
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

    private SimulationExecutioner simulationExecutioner;

    private App app;

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
        app.addKingdomLaunchedListener(this::onKingdomLaunched);
        app.addKingdomStoppedListener(this::onKingdomStopped);
        app.addBattleLaunchedListener(this::onBattleLaunched);
        app.addBattleStoppedListener(this::onBattleStopped);
        app.enableExecutingOptions();
        app.waitForAppClose();
        stopSimulation();

        this.simulationExecutioner = null;
        this.app = null;
    }

    private void stopSimulation() {
        for(var kv: kingdomMap.entrySet()) {
            Kingdom kingdom = kv.getValue();
            simulationExecutioner.stopKingdom(kingdom);
        }

        for(var kv: battleMap.entrySet()) {
            Battle battle = kv.getValue();
            simulationExecutioner.stopBattle(battle);
        }
        simulationExecutioner.waitForKingdomsDevelopingEnd();
        simulationExecutioner.waitForBattlesEnd();
    }

    private synchronized void onBattleStopped(Integer battleId) {
        if (!battleMap.containsKey(battleId)) {
            throw new BasicAppError("Incorrect battle id");
        }

        Battle battle = battleMap.get(battleId);
        simulationExecutioner.stopBattle(battle);
    }

    private synchronized void onBattleLaunched(BattleConfig battleConfig) {
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
        app.bindBattleSender(battle);
        battleMap.put(battleId, battle);
    }


    private synchronized void onKingdomLaunched(String kingdomId) {
        if(!kingdomMap.containsKey(kingdomId)) {
            throw new BasicAppError("Incorrect kingdom ID");
        }
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.launchKingdomDeveloping(kingdom, new TimeImpl(Integer.MAX_VALUE));
    }

    private synchronized void onKingdomStopped(String kingdomId) {
        if(!kingdomMap.containsKey(kingdomId)) {
            throw new BasicAppError("Incorrect kingdom ID");
        }
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.stopKingdom(kingdom);
    }

    private void createKingdom(KingdomConfig kingdomConfig) {
        Kingdom kingdom = simulationExecutioner.createKingdom(kingdomConfig);
        kingdomMap.put(kingdom.getId(), kingdom);
        app.bindKingdomSender(kingdom);
    }
}
