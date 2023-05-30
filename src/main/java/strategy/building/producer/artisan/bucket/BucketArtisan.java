package strategy.building.producer.artisan.bucket;

import strategy.building.producer.artisan.Artisan;
import strategy.material.Material;
import strategy.material.bar.Bar;
import strategy.product.tool.bucket.Bucket;

import java.util.function.Supplier;

public abstract class BucketArtisan<T extends Material, U extends Bucket> extends Artisan<T, U> {

    public BucketArtisan(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
        super(materialProducer, defaultStorageSize, craftingSpeed, durability);
    }

    @Override
    protected int getProducingTime() {
        return 24000;
    }
}
