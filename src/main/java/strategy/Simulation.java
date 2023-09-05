package strategy;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.config.SimulationConfigParser;
import strategy.json.JSON;
import strategy.json.JsonLoaderImpl;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.SarraxKingdom;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;
import strategy.military.InitMilitaryConfig;
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.Warrior;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simulation {

    private static SimulationConfig simulationConfig;

    //TODO gui:
    /*
    two panels with names of materials and count of each one:
    three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
    one button -> start battle
    mid panel -> battle results
    button to clear panel
    layer for error handling -> both for console and gui
     */
    public static void main(String[] args) {
        simulationConfig = createSimulationConfig();
        Kingdom firstKingdom = createKingdom(50000, simulationConfig.getFirstKingdomConfig());
        Kingdom weakerKingdom = createKingdom(25000, simulationConfig.getSecondKingdomConfig());
        simulateBattle(firstKingdom, weakerKingdom);
        firstKingdom.terminate();
        weakerKingdom.terminate();
    }

    private static SimulationConfig createSimulationConfig() {
        SimulationConfigParser configParser = new SimulationConfigParser();
        var loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");
        return configParser.createConfig(json);
    }

    private static void simulateBattle(Kingdom strongerKingdom, Kingdom weakerKingdom) {
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
    private static Kingdom createKingdom(long sleep, KingdomConfig kingdomConfig) {
        Kingdom kingdom = new SarraxKingdom(kingdomConfig);
        InitMilitaryConfig militaryConfig = kingdomConfig.getWarriorsConfig();
        addWarriorsToKingdom(kingdom, militaryConfig);
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        return kingdom;
    }

    private static void addWarriorsToKingdom(Kingdom kingdom, InitMilitaryConfig warriorsConfig) {
        int numberOfUnits = warriorsConfig.getNumberOfUnits();
        int maxDamage = warriorsConfig.getMaxDamage();
        int maxDefense = warriorsConfig.getMaxDefense();
        Collection<InfantryUnit> warriors = createWarriors(numberOfUnits, maxDamage, maxDefense);
        kingdom.addInfantry(warriors);
    }

    private static Collection<InfantryUnit> createWarriors(int numberOfWarriors, int maxDamage, int maxDefense) {
        return IntStream.range(0, numberOfWarriors).mapToObj(el -> createRandomWarrior(maxDamage, maxDefense)).collect(Collectors.toCollection(LinkedList::new));
    }

    private static Warrior createRandomWarrior(int maxDamage, int maxDefense) {
        Random rand = new Random();
        int damage = rand.nextInt(maxDamage);
        int defense = rand.nextInt(maxDefense);
        return new Warrior(damage, defense);
    }
}
