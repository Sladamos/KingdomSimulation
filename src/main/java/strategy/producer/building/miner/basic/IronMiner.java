package strategy.producer.building.miner.basic;

import strategy.item.mineral.ore.IronOre;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class IronMiner extends Miner<IronOre> {

    public IronMiner(OneItemStorage<IronOre> destinationStorage, ProducerConfig producerConfig) {
        super(destinationStorage, producerConfig);
    }

    @Override
    protected IronOre createNewItem() {
        return new IronOre();
    }
}
