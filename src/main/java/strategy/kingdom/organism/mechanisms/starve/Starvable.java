package strategy.kingdom.organism.mechanisms.starve;

public interface Starvable {
    void eat(int foodValue);
    void starve(int hungerValue);
    boolean isDead();
}
