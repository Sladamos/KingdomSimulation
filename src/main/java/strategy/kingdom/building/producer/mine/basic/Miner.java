package strategy.kingdom.building.producer.mine.basic;

import lombok.Getter;
import strategy.kingdom.building.Building;
import strategy.kingdom.building.exceptions.BuildingDestroyedException;
import strategy.kingdom.building.exceptions.IncorrectDamageException;
import strategy.kingdom.building.exceptions.IncorrectStorageException;
import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.material.mineral.Mineral;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Miner <T extends Mineral> implements Producer {

    private final Deque<T> storage;

    private final double miningSpeed;

    @Getter
    private int durability;

    private boolean isDestroyed;

    public Miner(int defaultStorageSize, double miningSpeed, int durability) {
        isDestroyed = false;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithOres(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                Thread.sleep((long) (15000 / miningSpeed));
                if(!isDestroyed()) {
                    T ore = createNewOre();
                    System.out.println("Produced :" + ore);
                    store(ore);
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public synchronized void dealDamage(int damage) {
        if(damage < 0) {
            throw new IncorrectDamageException("Damage must be a non negative number");
        }

        if(isDestroyed()) {
            throw new BuildingDestroyedException("It's not possible to attack destroyed building");
        }

        durability -= damage;
        durability = Math.max(0, durability);

        if(durability == 0) {
            isDestroyed = true;
            notifyAll();
        }
    }

    @Override
    public synchronized boolean isDestroyed() {
        return isDestroyed;
    }

    public synchronized void store(T ore) {
        storage.push(ore);
        notifyAll();
    }

    public synchronized int getNumberOfOresInStorage() {
        return storage.size();
    }

    public synchronized T getOre() {
        waitForOreInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract T createNewOre();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithOres(int numberOfOres) {
        for(int i = 0; i < numberOfOres; i++) {
            T ore = createNewOre();
            store(ore);
        }
    }

    private synchronized void waitForOreInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}
