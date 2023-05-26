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
import java.util.function.Supplier;

public abstract class Smelter <T extends Ore, S extends Bar> implements Producer {

    private final Deque<S> storage;

    private final double smeltingSpeed;

    private final Supplier<T> oresProducer;

    @Getter
    private int durability;

    private boolean isDestroyed;

    public Smelter(Supplier<T> oresProducer, int defaultStorageSize, double smeltingSpeed, int durability) {
        this.oresProducer = oresProducer;
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
            try {
                Thread.sleep((long) (18000 / smeltingSpeed));
                if(!isDestroyed()) {
                    S bar = createNewBar(oresProducer.get());
                    store(bar);
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

    protected abstract S createNewBar(T ore);

    protected abstract S createNewBar();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithBars(int numberOfBars) {
        for(int i = 0; i < numberOfBars; i++) {
            S bar = createNewBar();
            store(bar);
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
