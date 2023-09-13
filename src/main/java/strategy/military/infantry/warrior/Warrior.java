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
        isAlive = true;
    }

    @Override
    public void getHit(Attack attack) {
        Integer damage = attack.getAttackDamage();
        checkIfCanReceiveDamage();
        receiveDamage(damage);
    }

    @Override
    public synchronized boolean isDead() {
        return !isAlive;
    }

    private synchronized void checkIfCanReceiveDamage() {
        if(isDead()) {
            throw new FightActionException("Can't receive damage when is dead");
        }
    }

    private synchronized void receiveDamage(Integer damage) {
        int efficientDamage = Math.max(damage - defense, 0);
        hitPoints -= efficientDamage;
        hitPoints = Math.max(0, hitPoints);
        checkIfShouldBeKilled();
    }

    private synchronized void checkIfShouldBeKilled() {
        if(shouldBeKilled()) {
            isAlive = false;
        }
    }

    private synchronized boolean shouldBeKilled() {
        return hitPoints <= 0;
    }

    @Override
    public void attack(Fightable fightable) {
        Integer damage = this.damage * this.damageModifier;
        Attack attack = new AdvancedAttack(this, damage);
        fightable.getHit(attack);
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
