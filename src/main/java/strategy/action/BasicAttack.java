package strategy.action;

import lombok.Getter;
import strategy.error.CriticalAppError;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.Optional;

public class BasicAttack implements Attack {

	@Getter
	private final Optional<Fightable> attacker;

	@Getter
	private final Collection<Attack> combination;

	public BasicAttack(Fightable attacker, Collection<Attack> combination) {
		if(combination == null) {
			throw new CriticalAppError("Combination can't be unspecified.");
		}
		this.attacker = Optional.ofNullable(attacker);
		this.combination = combination;
	}

	@Override
	public synchronized void addToCombination(Attack attack) {
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
