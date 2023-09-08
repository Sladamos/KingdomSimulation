package strategy.item.military.infantry;

import lombok.Getter;
import lombok.Synchronized;
import strategy.item.organism.human.Human;
import strategy.mechanism.fight.exceptions.FightActionException;

public abstract class HumanInfantryUnit implements Human, InfantryUnit  {

    @Getter(onMethod_={@Synchronized})
    private final Integer damage;

    private final int defense;

    @Getter(onMethod_={@Synchronized})
    private int hitPoints;

    private boolean isAlive;

    public HumanInfantryUnit(InfantryConfig config) {
        this.damage = config.getDamage();
        this.defense = config.getDefense();
        this.hitPoints = config.getHealth();
        isAlive = true;
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
        return !isAlive;
    }
}
