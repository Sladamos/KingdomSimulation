package strategy.building.producer.mill;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.plant.Plant;
import strategy.product.flour.Flour;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Mill<T extends Plant, U extends Flour> implements Producer {

    private final Deque<U> storage;

    private final double millingSpeed;

    private final Supplier<T> plantProducer;

    @Getter(onMethod_={@Synchronized})
    private int durability;

    private boolean isDestroyed;

    public Mill(Supplier<T> plantProducer, int defaultStorageSize, double millingSpeed, int durability) {
        this.plantProducer = plantProducer;
        isDestroyed = false;
        this.durability = durability;
        this.millingSpeed = millingSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithFlours(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                T plant = plantProducer.get();
                System.out.println("Consumed :" + plant);
                Thread.sleep((long) (getMillingTime() / millingSpeed));
                if(!isDestroyed()) {
                    U flour = createNewFlour(plant);
                    System.out.println("Produced :" + flour);
                    store(flour);
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

    public synchronized void store(U flour) {
        storage.push(flour);
        notifyAll();
    }

    public synchronized int getNumberOfFloursInStorage() {
        return storage.size();
    }

    public synchronized U getFlour() {
        waitForFlourInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract U createNewFlour(T plant);

    protected abstract U createNewFlour();

    protected abstract int getMillingTime();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithFlours(int numberOfFlours) {
        for(int i = 0; i < numberOfFlours; i++) {
            U flour = createNewFlour();
            store(flour);
        }
    }

    private synchronized void waitForFlourInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}


