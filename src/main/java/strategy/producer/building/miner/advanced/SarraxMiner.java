package strategy.producer.building.miner.advanced;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.producer.building.miner.basic.RubyMiner;
import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.ore.IronOre;
import strategy.storage.OneItemStorage;

public class SarraxMiner extends TwoToTwoProducer<IronMiner, RubyMiner, IronOre, Ruby> implements Building {

    public SarraxMiner(OneItemStorage<IronOre> ironOreStorage, OneItemStorage<Ruby> rubyStorage) {
        super(new IronMiner(ironOreStorage, null), new RubyMiner(rubyStorage, null));
    }
}
