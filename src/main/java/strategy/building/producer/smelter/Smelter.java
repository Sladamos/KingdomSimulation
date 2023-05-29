package strategy.building.producer.smelter;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.bar.Bar;
import strategy.material.mineral.ore.Ore;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Smelter <T extends Ore, S extends Bar> implements Producer {

    private final Deque<S> storage;

    private final double smeltingSpeed;

    private final Supplier<T> oresProducer;

    @Getter(onMethod_={@Synchronized})
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
                T ore = oresProducer.get();
                System.out.println("Consumed :" + ore);
                Thread.sleep((long) (18000 / smeltingSpeed));
                if(!isDestroyed()) {
                    S bar = createNewBar(ore);
                    System.out.println("Produced :" + bar);
                    store(bar);
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
