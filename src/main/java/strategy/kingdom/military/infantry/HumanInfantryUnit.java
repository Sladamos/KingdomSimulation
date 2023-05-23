package strategy.kingdom.military.infantry;

import lombok.Getter;
import strategy.kingdom.organism.human.Human;
import strategy.kingdom.organism.mechanisms.fight.Fightable;

public abstract class HumanInfantryUnit extends Human implements InfantryUnit  {

    private static final int HUMAN_MAX_HITPOINTS = 100;

    private final int damage;

    private final int defense;

    @Getter
    private int hitPoints;

    private boolean isAlive;

    private final int maxHitPoints;

    public HumanInfantryUnit(int damage, int defense) {
        this.damage = damage;
        this.defense = defense;
        this.maxHitPoints = HUMAN_MAX_HITPOINTS;
        this.hitPoints = this.maxHitPoints;
        isAlive = true;
    }

    @Override
    public void attack(Fightable target) {
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
