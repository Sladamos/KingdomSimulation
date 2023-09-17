package strategy.producer.building.lumberjack;

import strategy.producer.ProducerConfig;
import strategy.producer.ZeroToOneProducer;
import strategy.item.wood.Wood;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Lumberjack<T extends Wood> extends ZeroToOneProducer<T>  implements Building {
    public Lumberjack(OneItemStorage<T> destinationStorage, ProducerConfig producerConfig) {
        super(destinationStorage, producerConfig);
    }
}