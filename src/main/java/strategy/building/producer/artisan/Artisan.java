package strategy.building.producer.artisan;

import strategy.building.producer.OneToOneProducer;
import strategy.material.Material;
import strategy.product.tool.Tool;

import java.util.function.Supplier;

public abstract class Artisan<T extends Material, U extends Tool> extends OneToOneProducer<T, U> {

    public Artisan(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getTool() {
        return getItem();
    }
}