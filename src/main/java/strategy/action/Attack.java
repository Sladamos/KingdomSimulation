package strategy.action;

import strategy.mechanism.fight.Fightable;

import java.util.Collection;

public interface Attack extends Action {
	Fightable getAttacker();
	Collection<Integer> getCombination();
	void addToCombination(Integer attack);
}