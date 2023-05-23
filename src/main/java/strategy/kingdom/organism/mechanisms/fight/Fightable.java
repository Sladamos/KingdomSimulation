package strategy.kingdom.organism.mechanisms.fight;

public interface Fightable {
    void attack(Fightable target);
    void getHit(int damage);
    boolean isDead();
}
