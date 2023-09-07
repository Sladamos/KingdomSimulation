package strategy.producer.building.artisan;

import strategy.producer.OneToOneProducer;
import strategy.item.Item;
import strategy.producer.building.Building;
import strategy.item.tool.Tool;

import java.util.function.Supplier;

public abstract class Artisan<T extends Item, U extends Tool> extends OneToOneProducer<T, U> implements Building {

    public Artisan(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getTool() {
        return getItem();
    }
}