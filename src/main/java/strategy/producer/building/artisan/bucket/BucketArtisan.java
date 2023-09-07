package strategy.producer.building.artisan.bucket;

import strategy.item.Item;
import strategy.item.tool.bucket.Bucket;
import strategy.producer.ProducerConfig;
import strategy.producer.building.artisan.Artisan;
import strategy.storage.OneItemStorage;

public abstract class BucketArtisan<T extends Item, U extends Bucket> extends Artisan<T, U> {

    public BucketArtisan(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}
