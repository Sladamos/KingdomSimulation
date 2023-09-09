package strategy.initializer;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.config.simulation.AutomaticSimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoader;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;
import strategy.simulation.AutomaticSimulationConfig;

public class AutomaticSimulationInitializer implements SimulationInitializer {

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
        simulateBattle(firstKingdom, weakerKingdom);
    }

    private AutomaticSimulationConfig createSimulationConfig() {
        AutomaticSimulationConfigParser configParser = new AutomaticSimulationConfigParser();
        JsonLoader loader = new JsonLoaderImpl();
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
