package strategy.military.infantry.warrior;

import lombok.Setter;
import strategy.action.AdvancedAttack;
import strategy.action.Attack;
import strategy.item.organism.human.Human;
import strategy.military.infantry.InfantryUnit;
import strategy.military.mechanism.fight.Fightable;
import strategy.military.mechanism.fight.exceptions.FightActionException;

public class Warrior implements Human, InfantryUnit {

    private final Integer damage;

    private final int defense;

    private int hitPoints;

    @Setter
    private int damageModifier;

    private boolean isAlive;

    public Warrior(WarriorConfig config) {
        this.damage = config.getDamage();
        this.defense = config.getDefense();
        this.hitPoints = config.getHealth();
        this.damageModifier = 1;
        isAlive = !shouldBeKilled();
    }

    @Override
    public synchronized void getHit(Attack attack) {
        Integer damage = attack.getAttackDamage();
        checkIfCanReceiveDamage();
        receiveDamage(damage);
    }

    @Override
    public boolean isDead() {
        return !isAlive;
    }

    @Override
    public synchronized Attack createAttack() {
        Integer damage = this.damage * this.damageModifier;
        return new AdvancedAttack(this, damage);
    }

    @Override
    public synchronized void attack(Fightable fightable) {
        Attack attack = createAttack();
        fightable.getHit(attack);
    }

    private void checkIfCanReceiveDamage() {
        if(isDead()) {
            throw new FightActionException("Can't receive damage when is dead");
        }
    }

    private void receiveDamage(Integer damage) {
        int efficientDamage = Math.max(damage - defense, 0);
        hitPoints -= efficientDamage;
        hitPoints = Math.max(0, hitPoints);
        checkIfShouldBeKilled();
    }

    private void checkIfShouldBeKilled() {
        if(shouldBeKilled()) {
            isAlive = false;
        }
    }

    private boolean shouldBeKilled() {
        return hitPoints <= 0;
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
