package strategy.kingdom.military.infantry;

import lombok.Getter;
import strategy.kingdom.organism.human.Human;
import strategy.kingdom.organism.mechanisms.fight.Fightable;
import strategy.kingdom.organism.mechanisms.fight.exceptions.FightActionException;
import strategy.kingdom.organism.mechanisms.fight.exceptions.IncorrectAttackException;
import strategy.kingdom.organism.mechanisms.fight.exceptions.IncorrectDefenseException;

public abstract class HumanInfantryUnit extends Human implements InfantryUnit  {

    private static final int HUMAN_MAX_HITPOINTS = 100;

    private final int damage;

    private final int defense;

    @Getter
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
    public void attack(Fightable target) {
        if(target == this) {
            throw new FightActionException("Can't attack itself");
        }
        
        target.getHit(damage);
    }

    @Override
    public void getHit(int damage) {
        int efficientDamage = Math.max(damage - defense, 0);
        hitPoints -= efficientDamage;
        hitPoints = Math.max(0, hitPoints);

        if(hitPoints == 0) {
            isAlive = false;
        }
    }

    @Override
    public boolean isDead() {
        return super.isDead() || !isAlive;
    }
}
