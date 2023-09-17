package strategy.action;

import lombok.Getter;
import strategy.error.CriticalAppError;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class AdvancedAttack implements Attack {

    @Getter
    private final Optional<Fightable> attacker;

    private final Collection<Attack> combination;

    private final Integer damage;

    public AdvancedAttack(Fightable attacker, Integer damage) {
        this.attacker = Optional.ofNullable(attacker);
        this.damage = damage;
        checkIsDamageNotNegative();
        combination = new LinkedList<>();
    }

    @Override
    public Collection<Attack> getCombination() {
        Collection<Attack> newCombination = new LinkedList<>(combination);
        newCombination.add(new AdvancedAttack(attacker.get(), damage));
        return newCombination;
    }

    @Override
    public synchronized void addToCombination(Attack attack) {
        combination.add(attack);
    }

    @Override
    public Integer getAttackDamage() {
        if(combination.isEmpty()) {
            return damage;
        }
        else {
            return damage + combination.parallelStream().map(Attack::getAttackDamage).reduce(0, Integer::sum);
        }
    }

    private void checkIsDamageNotNegative() {
        if (damage < 0) {
            throw new CriticalAppError("Attack damage can't be less than 0");
        }
    }
}
