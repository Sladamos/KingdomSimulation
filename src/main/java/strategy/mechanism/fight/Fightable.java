package strategy.mechanism.fight;

public interface Fightable {
    void attack(Fightable target);
    void getHit(int damage);
    boolean isDead();
}
