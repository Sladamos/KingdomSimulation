package strategy.producer.building.mill;

import strategy.producer.OneToOneProducer;
import strategy.item.plant.Plant;
import strategy.producer.building.Building;
import strategy.item.flour.Flour;

import java.util.function.Supplier;

public abstract class Mill<T extends Plant, U extends Flour> extends OneToOneProducer<T, U> implements Building {

    public Mill(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getFlour() {
        return getItem();
    }
}