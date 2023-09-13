package strategy.military.mechanism.fight;

import strategy.action.Attack;

public interface Fightable {
    void attack(Fightable fightable);
    void getHit(Attack attack);
    boolean isDead();
}
