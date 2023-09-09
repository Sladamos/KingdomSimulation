package strategy.initializer.military;

import strategy.config.infantry.WarriorConfigParser;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.warrior.WarriorConfig;

import java.util.Collection;
import java.util.stream.IntStream;

public class DefinedWarriorsInitializer implements WarriorsInitializer{

    private final WarriorConfigParser warriorConfigParser;

    public DefinedWarriorsInitializer() {
        warriorConfigParser = new WarriorConfigParser();
    }

    @Override
    public Collection<Warrior> createWarriors(InitWarriorsConfig warriorsConfig) {
        int numberOfWarriors = warriorsConfig.getNumberOfUnits();
        int damage = warriorsConfig.getMaxDamage();
        int defense = warriorsConfig.getMaxDefense();
        int health = warriorsConfig.getHealth();
        return IntStream.range(0, numberOfWarriors)
                .mapToObj(el -> warriorConfigParser.createConfig(damage, defense, health))
                .map(this::createWarrior)
                .toList();
    }

    @Override
    public Warrior createWarrior(WarriorConfig warriorConfig) {
        return new Warrior(warriorConfig);
    }
}
