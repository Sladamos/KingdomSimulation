package strategy.initializer.military;

import strategy.military.infantry.warrior.InitWarriorsConfig;
import strategy.military.infantry.warrior.Warrior;
import strategy.military.infantry.warrior.WarriorConfig;

import java.util.Collection;

public interface WarriorsInitializer {
    Collection<Warrior> createWarriors(InitWarriorsConfig warriorsConfig);
    Warrior createWarrior(WarriorConfig warriorConfig);
}
