package strategy.kingdom.building.producer.tool.bucket;

import strategy.kingdom.building.producer.tool.Artisan;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.product.tool.bucket.Bucket;

import java.util.function.Supplier;

public abstract class BucketArtisan<T extends Bar, U extends Bucket> extends Artisan<T, U> {

    public BucketArtisan(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
        super(materialProducer, defaultStorageSize, craftingSpeed, durability);
    }

    @Override
    protected int getCraftingTime() {
        return 24000;
    }
}
