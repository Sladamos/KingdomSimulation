package strategy.kingdom.building.producer.tool.bucket;

import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.material.bar.IronBar;
import strategy.kingdom.product.tool.bucket.Bucket;
import strategy.kingdom.product.tool.bucket.IronBucket;

import java.util.function.Supplier;

public class IronBucketArtisan extends BucketArtisan<IronBar, IronBucket> {

    private static final int IRON_BUCKET_ARTISAN_DURABILITY = 100;

    private static final int IRON_BUCKET_ARTISAN_CRAFTING_SPEED = 2;

    public IronBucketArtisan(Supplier<IronBar> materialProducer, int defaultStorageSize) {
        super(materialProducer, defaultStorageSize,
                IRON_BUCKET_ARTISAN_CRAFTING_SPEED, IRON_BUCKET_ARTISAN_DURABILITY);
    }

    @Override
    protected IronBucket createNewTool(IronBar material) {
        return new IronBucket(material);
    }

    @Override
    protected IronBucket createNewTool() {
        return new IronBucket();
    }
}
