package strategy;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;
import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.Warrior;

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
     */
    public static void main(String[] args) {
        Kingdom strongerKingdom = createStrongerKingdom();
        Kingdom weakerKingdom = createWeakerKingdom();
        simulateBattle(strongerKingdom, weakerKingdom);
        strongerKingdom.terminate();
        weakerKingdom.terminate();
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
        Kingdom strongerKingdom = createKingdom(50000, "Stronger");
        Collection<InfantryUnit> strongerWarriors = createWarriors(40, 25, 5);
        strongerKingdom.addInfantry(strongerWarriors);
        return strongerKingdom;
    }
    private static Kingdom createWeakerKingdom() {
        Kingdom weakerKingdom = createKingdom(25000, "Weaker");
        Collection<InfantryUnit> weakerWarriors = createWarriors(35, 20, 7);
        weakerKingdom.addInfantry(weakerWarriors);
        return weakerKingdom;
    }

    private static Kingdom createKingdom(long sleep, String kingdomId) {
        Kingdom kingdom = new SarraxKingdom(kingdomId);
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
