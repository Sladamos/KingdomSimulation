package strategy.producer.building.miner.basic;

import strategy.producer.ProducerConfig;
import strategy.producer.ZeroToOneProducer;
import strategy.item.mineral.Mineral;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Miner <T extends Mineral> extends ZeroToOneProducer<T>  implements Building {
    public Miner(OneItemStorage<T> destinationStorage, ProducerConfig producerConfig) {
        super(destinationStorage, producerConfig);
    }
}
