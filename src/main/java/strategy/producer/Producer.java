package strategy.producer;

public interface Producer extends Runnable {
	boolean isDestroyed();
	void dealDamage(int damage);
	int getDurability();
	void terminate();
}
