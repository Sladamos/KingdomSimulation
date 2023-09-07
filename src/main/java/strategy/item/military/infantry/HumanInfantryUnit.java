package strategy.item.military.infantry;

import lombok.Getter;
import lombok.Synchronized;
import strategy.item.organism.human.Human;
import strategy.mechanism.fight.Fightable;
import strategy.mechanism.fight.exceptions.FightActionException;
import strategy.mechanism.fight.exceptions.IncorrectAttackException;
import strategy.mechanism.fight.exceptions.IncorrectDefenseException;

public abstract class HumanInfantryUnit extends Human implements InfantryUnit  {

    private static final int HUMAN_MAX_HITPOINTS = 100;

    @Getter(onMethod_={@Synchronized})
    private final Integer damage;

    private final int defense;

    @Getter(onMethod_={@Synchronized})
    private int hitPoints;

    private boolean isAlive;

    public HumanInfantryUnit(int damage, int defense) {
        this.damage = damage;
        this.defense = defense;
        validateStatistics();
        this.hitPoints = HUMAN_MAX_HITPOINTS;
        isAlive = true;
    }

    private void validateStatistics() {
        if(damage < 0) {
            throw new IncorrectAttackException("Attack must be a not negative number");
        }

        if(defense < 0) {
            throw new IncorrectDefenseException("Defense must be a not negative number");
        }
    }

    @Override
    public synchronized void attack(Fightable target) {
        if(target == this) {
            throw new FightActionException("Can't attack itself");
        }

        if(isDead()) {
            throw new FightActionException("Can't attack when attacker is dead");
        }

        target.getHit(damage);
    }

    @Override
    public synchronized void getHit(int damage) {
        if(isDead()) {
            throw new FightActionException("Can't receive damage when is dead");
        }

        int efficientDamage = Math.max(damage - defense, 0);
        hitPoints -= efficientDamage;
        hitPoints = Math.max(0, hitPoints);

        if(hitPoints == 0) {
            isAlive = false;
        }
    }

    @Override
    public synchronized boolean isDead() {
        return super.isDead() || !isAlive;
    }
}
