package strategy.kingdom.organism.starve;

public interface Starvable {
    void eat(int foodValue);
    void starve(int hungerValue);
    boolean isDead();
}
