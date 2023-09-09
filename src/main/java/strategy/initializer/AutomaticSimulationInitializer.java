package strategy.initializer;

import strategy.SimulationConfig;
import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.config.SimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;

public class AutomaticSimulationInitializer implements SimulationInitializer {

    @Override
    public void initializeSimulation() {
        SimulationConfig simulationConfig = createSimulationConfig();
        KingdomInitializer kingdomInitializer = new KingdomInitializer();
        Kingdom firstKingdom = kingdomInitializer.createKingdom(50000, simulationConfig.getFirstKingdomConfig());
        Kingdom weakerKingdom = kingdomInitializer.createKingdom(25000, simulationConfig.getSecondKingdomConfig());
        simulateBattle(firstKingdom, weakerKingdom);
        firstKingdom.terminate();
        weakerKingdom.terminate();
    }

    private SimulationConfig createSimulationConfig() {
        SimulationConfigParser configParser = new SimulationConfigParser();
        var loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");
        return configParser.createConfig(json);
    }

    private void simulateBattle(Kingdom strongerKingdom, Kingdom weakerKingdom) {
        MessagesReceiver messagesReceiver = new ConsoleMessagesReceiver();
        Battle battle = new SimpleBattle(strongerKingdom, weakerKingdom, messagesReceiver);
        Thread thread = new Thread(battle);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
