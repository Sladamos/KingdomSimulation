package strategy.action;

import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;

public interface Attack extends Action {
	Fightable getAttacker();
	Collection<Attack> getCombination();
	void addToCombination(Attack attack);
	Integer getAttackDamage();
}
