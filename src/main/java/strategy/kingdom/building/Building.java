package strategy.kingdom.building;

public interface Building {
    boolean isDestroyed();
    void dealDamage(int damage);
    int getDurability();
}
