package strategy.action;

import lombok.Getter;
import strategy.mechanism.fight.Fightable;

import java.util.Collection;

public class AttackImpl implements Attack {

	@Getter
	private final Fightable attacker;

	@Getter
	private final Collection<Integer> combination;

	public AttackImpl(Fightable attacker, Collection<Integer> combination) {
		this.attacker = attacker;
		this.combination = combination;
	}

	@Override
	public void addToCombination(Integer attack) {
		combination.add(attack);
	}
}
