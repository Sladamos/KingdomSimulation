package strategy.producer.building.artisan.bucket;

import strategy.producer.building.artisan.Artisan;
import strategy.material.Material;
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
