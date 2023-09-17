package strategy.producer.building.artisan.bucket;

import strategy.item.bar.IronBar;
import strategy.item.tool.bucket.IronBucket;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class IronBucketArtisan extends BucketArtisan<IronBar, IronBucket> {

    public IronBucketArtisan(OneItemStorage<IronBar> sourceStorage, OneItemStorage<IronBucket> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected IronBucket createNewItem(IronBar material) {
        return new IronBucket(material);
    }
}
