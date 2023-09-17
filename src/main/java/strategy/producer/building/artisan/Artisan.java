package strategy.producer.building.artisan;

import strategy.item.Item;
import strategy.item.tool.Tool;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Artisan<T extends Item, U extends Tool> extends OneToOneProducer<T, U> implements Building {

    public Artisan(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}