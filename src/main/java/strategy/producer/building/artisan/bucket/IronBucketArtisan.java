package strategy.producer.building.artisan.bucket;

import strategy.item.bar.IronBar;
import strategy.item.tool.bucket.IronBucket;

import java.util.function.Supplier;

public class IronBucketArtisan extends BucketArtisan<IronBar, IronBucket> {

    private static final int IRON_BUCKET_ARTISAN_DURABILITY = 100;

    private static final int IRON_BUCKET_ARTISAN_CRAFTING_SPEED = 3;

    public IronBucketArtisan(Supplier<IronBar> materialProducer, int defaultStorageSize) {
        super(materialProducer, defaultStorageSize,
                IRON_BUCKET_ARTISAN_CRAFTING_SPEED, IRON_BUCKET_ARTISAN_DURABILITY);
    }

    @Override
    protected IronBucket createNewItem(IronBar material) {
        return new IronBucket(material);
    }

    @Override
    protected IronBucket produceNewItem() {
        return new IronBucket();
    }
}
