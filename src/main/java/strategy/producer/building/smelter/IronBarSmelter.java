package strategy.producer.building.smelter;

import strategy.item.bar.IronBar;
import strategy.item.mineral.ore.IronOre;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class IronBarSmelter extends Smelter<IronOre, IronBar> {

    public IronBarSmelter(OneItemStorage<IronOre> sourceStorage, OneItemStorage<IronBar> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected IronBar createNewItem(IronOre ore) {
        return new IronBar(ore);
    }
}
