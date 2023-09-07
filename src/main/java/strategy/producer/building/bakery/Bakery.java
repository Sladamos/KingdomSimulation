package strategy.producer.building.bakery;

import strategy.producer.TwoToOneProducer;
import strategy.item.Item;
import strategy.producer.building.Building;
import strategy.item.flour.Flour;
import strategy.item.food.baking.Baking;

import java.util.function.Supplier;

public abstract class Bakery<T extends Flour, U extends Item, V extends Baking> extends TwoToOneProducer<T, U, V>
        implements Building {

    public Bakery(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
        super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized V getBaking() {
        return getItem();
    }
}