package strategy.producer.building.miner.advanced;

import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.ore.IronOre;
import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.producer.building.miner.basic.RubyMiner;

public class SarraxMiner extends TwoToTwoProducer<IronMiner, RubyMiner, IronOre, Ruby> implements Building {

    public SarraxMiner(IronMiner ironMiner, RubyMiner rubyMiner) {
        super(ironMiner, rubyMiner);
    }
}
