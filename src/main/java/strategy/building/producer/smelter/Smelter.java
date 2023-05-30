package strategy.building.producer.smelter;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.OneToOneProducer;
import strategy.building.producer.Producer;
import strategy.material.bar.Bar;
import strategy.material.mineral.ore.Ore;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Smelter <T extends Ore, S extends Bar> extends OneToOneProducer<T, S> {

    public Smelter(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    @Override
    protected int getProducingTime() {
        return 18000;
    }

    public synchronized S getBar() {
        return getItem();
    }

    public synchronized int getNumberOfBarsInStorage() {
        return getNumberOfItemsInStorage();
    }
}
