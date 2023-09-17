package strategy.producer.building.smelter;

import strategy.item.bar.Bar;
import strategy.item.mineral.ore.Ore;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Smelter <T extends Ore, S extends Bar> extends OneToOneProducer<T, S>  implements Building {

    public Smelter(OneItemStorage<T> sourceStorage, OneItemStorage<S> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}
