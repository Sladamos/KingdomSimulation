package strategy.building.producer.mill;

import strategy.building.producer.OneToOneProducer;
import strategy.material.plant.Plant;
import strategy.product.flour.Flour;

import java.util.function.Supplier;

public abstract class Mill<T extends Plant, U extends Flour> extends OneToOneProducer<T, U> {

    public Mill(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getFlour() {
        return getItem();
    }
}