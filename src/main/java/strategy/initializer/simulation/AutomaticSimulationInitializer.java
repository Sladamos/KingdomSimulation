package strategy.initializer.simulation;

import strategy.app.App;
import strategy.app.communicator.AppCommunicator;
import strategy.battle.Battle;
import strategy.battle.creator.BattleCreator;
import strategy.config.simulation.AutomaticSimulationConfigParser;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.json.JSON;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.AutomaticSimulationConfig;
import strategy.simulation.api.SimulationAPI;
import strategy.simulation.executioner.SimulationExecutioner;

import java.util.LinkedList;
import java.util.List;

public class AutomaticSimulationInitializer implements SimulationInitializer {

    private AppCommunicator appCommunicator;

    private SimulationExecutioner simulationExecutioner;

    private final BattleCreator battleCreator;

    private final AutomaticSimulationConfig simulationConfig;

    private final List<Kingdom> kingdoms;

    public AutomaticSimulationInitializer(BattleCreator battleCreator) {
        this.battleCreator = battleCreator;
        simulationConfig = createSimulationConfig();
        kingdoms = new LinkedList<>();
    }


    @Override
    public void initializeSimulation(App app, SimulationAPI simulationAPI) {
        this.appCommunicator = app;
        this.simulationExecutioner = simulationAPI;
        runKingdomsDeveloping();
        simulationAPI.waitForKingdomsDevelopingEnd();
        runBattleSimulation();
        simulationAPI.waitForBattlesEnd();

        this.appCommunicator = null;
        this.simulationExecutioner = null;
    }

    private void runBattleSimulation() {
        List<Kingdom> checkedKingdoms = new LinkedList<>();
        for(Kingdom kingdom: kingdoms) {
            if(!checkedKingdoms.contains(kingdom)) {
                for(Kingdom anotherKingdom: kingdoms) {
                    Battle battle = battleCreator.createBattle(kingdom, anotherKingdom);
                    simulateBattle(battle);
                }
            }
            checkedKingdoms.add(kingdom);
        }
    }

    private void runKingdomsDeveloping() {
        Kingdom firstKingdom = createKingdom(simulationConfig.getFirstKingdomConfig());
        Kingdom secondKingdom = createKingdom(simulationConfig.getSecondKingdomConfig());
        simulationExecutioner.launchKingdomDeveloping(firstKingdom, simulationConfig.getFirstKingdomDevelopmentTime());
        simulationExecutioner.launchKingdomDeveloping(secondKingdom, simulationConfig.getSecondKingdomDevelopmentTime());
        kingdoms.add(firstKingdom);
        kingdoms.add(secondKingdom);
    }

    private AutomaticSimulationConfig createSimulationConfig() {
        AutomaticSimulationConfigParser configParser = new AutomaticSimulationConfigParser();
        FileJsonLoader loader = new FileJsonLoaderImpl();
        loader.setFileName("config.json");
        JSON json = loader.loadJson();
        return configParser.createConfig(json);
    }

    private Kingdom createKingdom(KingdomConfig kingdomConfig) {
        Kingdom kingdom = simulationExecutioner.createKingdom(kingdomConfig);
        appCommunicator.bindKingdomSender(kingdom);
        return kingdom;
    }

    private void simulateBattle(Battle battle) {
        appCommunicator.bindBattleSender(battle);
        simulationExecutioner.launchBattle(battle);
    }
}
