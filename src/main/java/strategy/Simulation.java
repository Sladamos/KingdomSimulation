package strategy;

import org.json.JSONObject;
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
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.Warrior;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Simulation {

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
        SimulationConfigParser configParser = new SimulationConfigParser();
        var loader = new JsonLoaderImpl();
        JSON json = loader.loadJsonFromFile("config.json");

        // Parse the JSON content into a JSONObject
        //configParser.createSimulationConfig(jsonObject);
//
//        Kingdom strongerKingdom = createStrongerKingdom();
//        Kingdom weakerKingdom = createWeakerKingdom();
//        simulateBattle(strongerKingdom, weakerKingdom);
//        strongerKingdom.terminate();
//        weakerKingdom.terminate();
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

    private static Kingdom createStrongerKingdom() {
        Kingdom strongerKingdom = createKingdom(50000, null);
        Collection<InfantryUnit> strongerWarriors = createWarriors(40, 25, 5);
        strongerKingdom.addInfantry(strongerWarriors);
        return strongerKingdom;
    }
    private static Kingdom createWeakerKingdom() {
        Kingdom weakerKingdom = createKingdom(25000, null);
        Collection<InfantryUnit> weakerWarriors = createWarriors(35, 20, 7);
        weakerKingdom.addInfantry(weakerWarriors);
        return weakerKingdom;
    }

    private static Kingdom createKingdom(long sleep, KingdomConfig kingdomConfig) {
        Kingdom kingdom = new SarraxKingdom(kingdomConfig);
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        return kingdom;
    }

    private static Collection<InfantryUnit> createWarriors(int numberOfWarriors, int maxDamage, int maxDefense) {
        Collection<InfantryUnit> warriors = new LinkedList<>();
        Random rand = new Random();
        while(numberOfWarriors > 0) {
            numberOfWarriors--;
            int damage = rand.nextInt(maxDamage);
            int defense = rand.nextInt(maxDefense);
            warriors.add(new Warrior(damage, defense));
        }
        return warriors;
    }
}
