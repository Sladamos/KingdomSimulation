package strategy.building.producer.bakery.bread;

import strategy.building.producer.bakery.Bakery;
import strategy.material.mineral.Salt;
import strategy.product.flour.Flour;
import strategy.product.food.baking.bread.Bread;

import java.util.function.Supplier;

public abstract class BreadBakery<T extends Flour, U extends Bread, V extends Salt> extends Bakery<T, U, V> {

    public BreadBakery(Supplier<T> flourProducer, Supplier<V> materialProducer, int defaultStorageSize, double bakingSpeed, int durability) {
        super(flourProducer, materialProducer, defaultStorageSize, bakingSpeed, durability);
    }

    @Override
    protected int getBakingTime() {
        return 30000;
    }
}
