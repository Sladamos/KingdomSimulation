package strategy.building.producer.bakery;

import strategy.building.producer.TwoToOneProducer;
import strategy.material.Material;
import strategy.product.flour.Flour;
import strategy.product.food.baking.Baking;

import java.util.function.Supplier;

public abstract class Bakery<T extends Flour, U extends Material, V extends Baking> extends TwoToOneProducer<T, U, V> {

    public Bakery(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
        super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized V getBaking() {
        return getItem();
    }
}