package strategy.building.producer.bakery.bread;

import strategy.building.producer.bakery.Bakery;
import strategy.product.flour.Flour;
import strategy.product.food.baking.bread.Bread;

import java.util.function.Supplier;

public abstract class BreadBakery<T extends Flour, U extends Bread> extends Bakery<T, U> {

    public BreadBakery(Supplier<T> flourProducer, int defaultStorageSize, double bakingSpeed, int durability) {
        super(flourProducer, defaultStorageSize, bakingSpeed, durability);
    }

    @Override
    protected int getBakingTime() {
        return 30000;
    }
}
