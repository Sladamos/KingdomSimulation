package strategy.action;

import lombok.Getter;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;

public class BasicAttack implements Attack {

	@Getter
	private final Fightable attacker;

	@Getter
	private final Collection<Attack> combination;

	public BasicAttack(Fightable attacker, Collection<Attack> combination) {
		this.attacker = attacker;
		this.combination = combination;
	}

	@Override
	public void addToCombination(Attack attack) {
		combination.add(attack);
	}

	@Override
	public Integer getAttackDamage() {
		if(combination.isEmpty()) {
			return 0;
		} else {
			return combination.parallelStream().map(Attack::getAttackDamage).reduce(0, Integer::sum);
		}
	}
}
