package strategy.building;

public interface Building {
    boolean isDestroyed();
    void dealDamage(int damage);
    int getDurability();
}
