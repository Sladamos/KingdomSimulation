package strategy.building.producer.miner.basic;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.mineral.Mineral;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Miner <T extends Mineral> implements Producer {

    private final Deque<T> storage;

    private final double miningSpeed;

    @Getter(onMethod_={@Synchronized})
    private int durability;

    private boolean isDestroyed;

    public Miner(int defaultStorageSize, double miningSpeed, int durability) {
        isDestroyed = false;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithMinerals(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                Thread.sleep((long) (15000 / miningSpeed));
                if(!isDestroyed()) {
                    T material = createNewMineral();
                    System.out.println("Produced :" + material);
                    store(material);
                }
            } catch (InterruptedException ignored) {
                return;
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

    public synchronized void store(T material) {
        storage.push(material);
        notifyAll();
    }

    public synchronized int getNumberOfMaterialsInStorage() {
        return storage.size();
    }

    public synchronized T getMaterial() {
        waitForMineralInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract T createNewMineral();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithMinerals(int numberOfOres) {
        for(int i = 0; i < numberOfOres; i++) {
            T mineral = createNewMineral();
            store(mineral);
        }
    }

    private synchronized void waitForMineralInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}
