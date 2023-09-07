package strategy.producer.building.mill;

import strategy.item.flour.Flour;
import strategy.item.plant.Plant;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Mill<T extends Plant, U extends Flour> extends OneToOneProducer<T, U> implements Building {

    public Mill(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}