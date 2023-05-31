package strategy.producer.building.smelter;

import strategy.producer.OneToOneProducer;
import strategy.material.bar.Bar;
import strategy.material.mineral.ore.Ore;
import strategy.producer.building.Building;

import java.util.function.Supplier;

public abstract class Smelter <T extends Ore, S extends Bar> extends OneToOneProducer<T, S>  implements Building {

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
