package strategy.kingdom.organism;

public interface Starvable {
    void eat(int foodValue);
    void starve(int hungerValue);
    boolean isDead();
}
