package strategy.mechanism.starve;

public interface Starvable {
    void eat(int foodValue);
    void starve(int hungerValue);
    boolean isDead();
}
