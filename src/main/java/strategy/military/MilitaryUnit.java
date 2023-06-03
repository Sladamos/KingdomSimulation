package strategy.military;

import strategy.organism.mechanisms.fight.Fightable;

public interface MilitaryUnit extends Fightable {
    Integer getDamage();
}
