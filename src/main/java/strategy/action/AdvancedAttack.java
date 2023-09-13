package strategy.action;

import lombok.Getter;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.LinkedList;

public class AdvancedAttack implements Attack {

    @Getter
    private final Fightable attacker;

    private final Collection<Attack> combination;

    private final Integer damage;

    public AdvancedAttack(Fightable attacker, Integer damage) {
        this.attacker = attacker;
        this.damage = damage;
        combination = new LinkedList<>();
    }

    @Override
    public Collection<Attack> getCombination() {
        Collection<Attack> newCombination = new LinkedList<>(combination);
        newCombination.add(new AdvancedAttack(attacker, damage));
        return newCombination;
    }

    @Override
    public void addToCombination(Attack attack) {
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
}
