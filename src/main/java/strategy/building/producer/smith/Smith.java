package strategy.building.producer.smith;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.Producer;
import strategy.material.Material;
import strategy.product.weapon.Weapon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Smith <T extends Material, U extends Weapon> implements Producer {

    private final Deque<U> storage;

    private final double forgingSpeed;

    private final Supplier<T> materialProducer;

    @Getter(onMethod_={@Synchronized})
    private int durability;

    private boolean isDestroyed;

    public Smith(Supplier<T> materialProducer, int defaultStorageSize, double forgingSpeed, int durability) {
        this.materialProducer = materialProducer;
        isDestroyed = false;
        this.durability = durability;
        this.forgingSpeed = forgingSpeed;
        storage = new ArrayDeque<>();
        checkInitParameters(defaultStorageSize);
        initiallyFillStorageWithWeapons(defaultStorageSize);
    }

    @Override
    public void run() {
        while(!isDestroyed()) {
            try {
                T material = materialProducer.get();
                System.out.println("Consumed :" + material);
                Thread.sleep((long) (getForgingTime() / forgingSpeed));
                if(!isDestroyed()) {
                    U weapon = forgeNewWeapon(material);
                    System.out.println("Produced :" + weapon);
                    store(weapon);
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

    public synchronized void store(U weapon) {
        storage.push(weapon);
        notifyAll();
    }

    public synchronized int getNumberOfWeaponsInStorage() {
        return storage.size();
    }

    public synchronized U getWeapon() {
        waitForWeaponInStorage();
        if(isDestroyed()) {
            throw new BuildingDestroyedException();
        }
        return storage.pop();
    }

    protected abstract U forgeNewWeapon(T material);

    protected abstract U forgeNewWeapon();

    protected abstract int getForgingTime();

    private void checkInitParameters(int storageSize) {
        if (storageSize < 0) {
            throw new IncorrectStorageException("Storage size must be a non negative number");
        }
    }

    private void initiallyFillStorageWithWeapons(int numberOfWeapons) {
        for(int i = 0; i < numberOfWeapons; i++) {
            U weapon = forgeNewWeapon();
            store(weapon);
        }
    }

    private synchronized void waitForWeaponInStorage() {
        while(storage.size() == 0 && !isDestroyed()) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
    }
}

