package strategy.initializer.military;

import strategy.config.infantry.WarriorConfigParser;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.warrior.WarriorConfig;

import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomWarriorsInitializer implements WarriorsInitializer {

    private final Random rand;

    private final WarriorConfigParser warriorConfigParser;

    public RandomWarriorsInitializer() {
        warriorConfigParser = new WarriorConfigParser();
        rand = new Random();
    }

    @Override
    public Collection<Warrior> createWarriors(InitWarriorsConfig warriorsConfig) {
        int numberOfWarriors = warriorsConfig.getNumberOfUnits();
        int damage = warriorsConfig.getMaxDamage();
        int defense = warriorsConfig.getMaxDefense();
        int health = warriorsConfig.getHealth();
        return IntStream.range(0, numberOfWarriors)
                .mapToObj(el -> createRandomWarriorConfig(damage, defense, health))
                .map(this::createWarrior)
                .toList();
    }

    @Override
    public Warrior createWarrior(WarriorConfig warriorConfig) {
        return new Warrior(warriorConfig);
    }

    private WarriorConfig createRandomWarriorConfig(int damage, int defense, int health) {
        int randomDamage = rand.nextInt() % damage;
        int randomDefense = rand.nextInt() % defense;
        return warriorConfigParser.createConfig(randomDamage, randomDefense, health);
    }
}
