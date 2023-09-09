package strategy.initializer.military;

import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.warrior.WarriorConfig;

import java.util.Collection;

public interface WarriorsInitializer {
    Collection<Warrior> createWarriors(InitWarriorsConfig warriorsConfig);
    Warrior createWarrior(WarriorConfig warriorConfig);
}
