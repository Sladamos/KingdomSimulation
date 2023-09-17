package strategy.producer.building.miner.basic;

import strategy.item.mineral.gem.Ruby;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class RubyMiner extends Miner<Ruby> {

    public RubyMiner(OneItemStorage<Ruby> destinationStorage, ProducerConfig producerConfig) {
        super(destinationStorage, producerConfig);
    }

    @Override
    protected Ruby createNewItem() {
        return new Ruby();
    }
}
