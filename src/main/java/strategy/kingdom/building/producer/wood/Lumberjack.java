package strategy.kingdom.building.producer.wood;

import lombok.Getter;
import strategy.kingdom.building.Building;
import strategy.kingdom.building.exceptions.BuildingDestroyedException;
import strategy.kingdom.building.exceptions.IncorrectDamageException;
import strategy.kingdom.building.exceptions.IncorrectStorageException;
import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.material.wood.Wood;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Lumberjack<T extends Wood> implements Producer {

    private final Deque<T> storage;

    private final double producingSpeed;

    @Getter
    private int durability;

    private boolean isDestroyed;

    public Lumberjack(int defaultStorageSize, double producingSpeed, int durability) {
        isDestroyed = false;
        this.durability = durability;
        this.producingSpeed = producingSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithWoods(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                Thread.sleep((long) (25000 / producingSpeed));
                if(!isDestroyed()) {
                    T wood = createNewWood();
                    store(wood);
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

    public synchronized void store(T wood) {
        storage.push(wood);
        notifyAll();
    }

    public synchronized int getNumberOfWoodsInStorage() {
        return storage.size();
    }

    public synchronized T getWood() {
        waitForWoodInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract T createNewWood();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithWoods(int numberOfWoods) {
        for(int i = 0; i < numberOfWoods; i++) {
            T wood = createNewWood();
            store(wood);
        }
    }

    private synchronized void waitForWoodInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}
