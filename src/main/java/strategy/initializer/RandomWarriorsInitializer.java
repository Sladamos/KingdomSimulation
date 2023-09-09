package strategy.initializer;

import strategy.item.military.InitMilitaryConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.warrior.WarriorConfig;

import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomWarriorsInitializer {

    private final Random rand;

    public RandomWarriorsInitializer() {
        rand = new Random();
    }

    public Collection<Warrior> createWarriors(InitMilitaryConfig warriorsConfig) {
        int numberOfWarriors = warriorsConfig.getNumberOfUnits();
        int maxDamage = warriorsConfig.getMaxDamage();
        int maxDefense = warriorsConfig.getMaxDefense();
        int health = warriorsConfig.getHealth();
        return IntStream.range(0, numberOfWarriors).mapToObj(el -> createRandomWarrior(maxDamage, maxDefense, health)).toList();
    }

    private Warrior createRandomWarrior(int maxDamage, int maxDefense, int health) {
        int damage = rand.nextInt(maxDamage);
        int defense = rand.nextInt(maxDefense);
        WarriorConfig warriorConfig = new WarriorConfig(damage, defense, health);
        return new Warrior(warriorConfig);
    }
}
