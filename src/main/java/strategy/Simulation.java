package strategy;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.kingdom.Kingdom;
import strategy.kingdom.SarraxKingdom;
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.Warrior;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Simulation {

    public static void main(String[] args) {
        Kingdom strongerKingdom = createStrongerKingdom();
        Kingdom weakerKingdom = createWeakerKingdom();
        simulateBattle(strongerKingdom, weakerKingdom);
        strongerKingdom.terminate();
        weakerKingdom.terminate();
    }

    private static void simulateBattle(Kingdom strongerKingdom, Kingdom weakerKingdom) {
        Battle battle = new SimpleBattle(strongerKingdom, weakerKingdom);
        Thread thread = new Thread(battle);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Kingdom createStrongerKingdom() {
        Kingdom strongerKingdom = createKingdom(50000);
        Collection<InfantryUnit> strongerWarriors = createWarriors(40, 25, 5);
        strongerKingdom.addInfantry(strongerWarriors);
        return strongerKingdom;
    }
    private static Kingdom createWeakerKingdom() {
        Kingdom weakerKingdom = createKingdom(25000);
        Collection<InfantryUnit> weakerWarriors = createWarriors(35, 20, 7);
        weakerKingdom.addInfantry(weakerWarriors);
        return weakerKingdom;
    }

    private static Kingdom createKingdom(long sleep) {
        Kingdom kingdom = new SarraxKingdom();
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
