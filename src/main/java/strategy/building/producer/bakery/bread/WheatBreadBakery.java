package strategy.building.producer.bakery.bread;

import strategy.product.flour.WheatFlour;
import strategy.product.food.baking.bread.WheatBread;

import java.util.function.Supplier;

public class WheatBreadBakery extends BreadBakery<WheatFlour, WheatBread> {

    private static final int WHEAT_BREAD_BAKERY_DURABILITY = 140;

    private static final int WHEAT_BREAD_BAKERY_BAKING_SPEED = 2;

    public WheatBreadBakery(Supplier<WheatFlour> flourProducer, int defaultStorageSize) {
        super(flourProducer, defaultStorageSize, WHEAT_BREAD_BAKERY_BAKING_SPEED,
                WHEAT_BREAD_BAKERY_DURABILITY);
    }

    @Override
    protected WheatBread createNewBaking(WheatFlour flour) {
        return new WheatBread();
    }

    @Override
    protected WheatBread createNewBaking() {
        return new WheatBread();
    }
}
