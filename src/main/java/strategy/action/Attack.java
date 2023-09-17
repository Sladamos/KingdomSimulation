package strategy.action;

import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.Optional;

public interface Attack extends Action {
	Optional<Fightable> getAttacker();
	Collection<Attack> getCombination();
	void addToCombination(Attack attack);
	Integer getAttackDamage();
}
