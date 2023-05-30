package strategy.building.producer.bakery.bread;

import strategy.material.mineral.Salt;
import strategy.product.flour.WheatFlour;
import strategy.product.food.baking.bread.WheatBread;

import java.util.function.Supplier;

public class WheatBreadBakery extends BreadBakery<WheatFlour, Salt, WheatBread> {

    private static final int WHEAT_BREAD_BAKERY_DURABILITY = 140;

    private static final int WHEAT_BREAD_BAKERY_BAKING_SPEED = 2;

    public WheatBreadBakery(Supplier<WheatFlour> flourProducer, Supplier<Salt> saltProducer, int defaultStorageSize) {
        super(flourProducer, saltProducer, defaultStorageSize, WHEAT_BREAD_BAKERY_BAKING_SPEED,
                WHEAT_BREAD_BAKERY_DURABILITY);
    }

    @Override
    protected WheatBread produceNewItem(WheatFlour flour, Salt salt) {
        return new WheatBread();
    }

    @Override
    protected WheatBread produceNewItem() {
        return new WheatBread();
    }
}
