package strategy.kingdom.building.producer.bar;

import lombok.Getter;
import strategy.kingdom.building.exceptions.BuildingDestroyedException;
import strategy.kingdom.building.exceptions.IncorrectDamageException;
import strategy.kingdom.building.exceptions.IncorrectStorageException;
import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.material.mineral.ore.Ore;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Smelter <T extends Ore, S extends Bar> implements Producer {

    private final Deque<S> storage;

    private final double smeltingSpeed;

    @Getter
    private int durability;

    private boolean isDestroyed;

    public Smelter(int defaultStorageSize, double smeltingSpeed, int durability) {
        isDestroyed = false;
        this.durability = durability;
        this.smeltingSpeed = smeltingSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithBars(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            S bar = createNewBar();
            store(bar);
            try {
                Thread.sleep((long) (18000 / smeltingSpeed));
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

    public synchronized void store(S bar) {
        storage.push(bar);
        notifyAll();
    }

    public synchronized int getNumberOfBarsInStorage() {
        return storage.size();
    }

    public synchronized S getBar() {
        waitForBarInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract S createNewBar();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithBars(int numberOfBars) {
        for(int i = 0; i < numberOfBars; i++) {
            S ore = createNewBar();
            store(S);
        }
    }

    private synchronized void waitForBarInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}
