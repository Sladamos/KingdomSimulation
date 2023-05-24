package strategy.kingdom.building.mine;

import strategy.kingdom.building.Building;
import strategy.kingdom.material.ore.Ore;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Miner <T extends Ore> implements Runnable, Building {

    private final Deque<T> storage;

    final double miningSpeed;

    private int durability;

    private boolean isDestroyed;

    public Miner(int defaultStorageSize, double miningSpeed, int durability) {
        isDestroyed = false;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        storage = new ArrayDeque<>();
        initiallyFillStorageWithOres(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            T ore = createNewOre();
            putOreToStorage(ore);
            try {
                Thread.sleep((long) (15 / miningSpeed));
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public synchronized void dealDamage(int damage) {
        durability -= damage;
        durability = Math.max(0, durability);
    }

    @Override
    public synchronized boolean isDestroyed() {
        return isDestroyed;
    }

    public synchronized void putOreToStorage(T ore) {
        storage.push(ore);
    }

    public synchronized int getNumberOfOresInStorage() {
        return storage.size();
    }

    public synchronized T getOre() {
        waitForOreInStorage();
        return storage.pop();
    }

    protected abstract T createNewOre();

    private void initiallyFillStorageWithOres(int numberOfOres) {
        for(int i = 0; i < numberOfOres; i++) {
            T ore = createNewOre();
            putOreToStorage(ore);
        }
    }

    private synchronized void waitForOreInStorage() {
        while(storage.size() == 0) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}
