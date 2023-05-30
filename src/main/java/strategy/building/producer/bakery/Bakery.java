package strategy.building.producer.bakery;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.Material;
import strategy.product.flour.Flour;
import strategy.product.food.baking.Baking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Bakery<T extends Flour, U extends Baking, V extends Material> implements Producer {

    private final Deque<U> storage;

    private final double bakingSpeed;

    private final Supplier<T> flourProducer;

    private final Supplier<V> materialProducer;

    @Getter(onMethod_={@Synchronized})
    private int durability;

    private boolean isDestroyed;

    public Bakery(Supplier<T> flourProducer, Supplier<V> materialProducer, int defaultStorageSize, double bakingSpeed, int durability) {
        this.flourProducer = flourProducer;
        isDestroyed = false;
        this.durability = durability;
        this.bakingSpeed = bakingSpeed;
        this.materialProducer = materialProducer;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithBakings(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                T flour = flourProducer.get();
                System.out.println("Consumed :" + flour);
                V material = materialProducer.get();
                System.out.println("Consumed :" + material);
                Thread.sleep((long) (getBakingTime() / bakingSpeed));
                if(!isDestroyed()) {
                    U baking = createNewBaking(flour);
                    System.out.println("Produced :" + baking);
                    store(baking);
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

    public synchronized void store(U baking) {
        storage.push(baking);
        notifyAll();
    }

    public synchronized int getNumberOfBakingsInStorage() {
        return storage.size();
    }

    public synchronized U getBaking() {
        waitForBakingInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract U createNewBaking(T flour);

    protected abstract U createNewBaking();

    protected abstract int getBakingTime();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithBakings(int numberOfBakings) {
        for(int i = 0; i < numberOfBakings; i++) {
            U baking = createNewBaking();
            store(baking);
        }
    }

    private synchronized void waitForBakingInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}


