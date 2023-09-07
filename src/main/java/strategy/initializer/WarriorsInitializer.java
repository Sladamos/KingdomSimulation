package strategy.initializer;

import strategy.military.InitMilitaryConfig;
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.warrior.Warrior;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WarriorsInitializer {

    private final Random rand;

    public WarriorsInitializer() {
        rand = new Random();
    }

    public Collection<InfantryUnit> createWarriors(InitMilitaryConfig warriorsConfig) {
        int numberOfWarriors = warriorsConfig.getNumberOfUnits();
        int maxDamage = warriorsConfig.getMaxDamage();
        int maxDefense = warriorsConfig.getMaxDefense();
        return IntStream.range(0, numberOfWarriors).mapToObj(el -> createRandomWarrior(maxDamage, maxDefense)).collect(Collectors.toCollection(LinkedList::new));
    }

    private Warrior createRandomWarrior(int maxDamage, int maxDefense) {
        int damage = rand.nextInt(maxDamage);
        int defense = rand.nextInt(maxDefense);
        return new Warrior(damage, defense);
    }
}
