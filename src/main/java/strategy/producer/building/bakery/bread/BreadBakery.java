package strategy.producer.building.bakery.bread;

import strategy.producer.building.bakery.Bakery;
import strategy.material.mineral.Salt;
import strategy.product.flour.Flour;
import strategy.product.food.baking.bread.Bread;

import java.util.function.Supplier;

public abstract class BreadBakery<T extends Flour, U extends Salt, V extends Bread> extends Bakery<T, U, V> {

    public BreadBakery(Supplier<T> flourProducer, Supplier<U> materialProducer, int defaultStorageSize, double bakingSpeed, int durability) {
        super(flourProducer, materialProducer, defaultStorageSize, bakingSpeed, durability);
    }

    @Override
    protected int getProducingTime() {
        return 30000;
    }
}
