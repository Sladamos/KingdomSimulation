package strategy.building.producer.miner.advanced;

import strategy.building.producer.TwoToTwoProducer;
import strategy.building.producer.miner.basic.IronMiner;
import strategy.building.producer.miner.basic.RubyMiner;
import strategy.material.mineral.gem.Ruby;
import strategy.material.mineral.ore.IronOre;

public class SarraxMiner extends TwoToTwoProducer<IronMiner, RubyMiner, IronOre, Ruby> {

    public SarraxMiner() {
        super(new IronMiner(15), new RubyMiner(10));
    }

    public synchronized IronOre getIronOre() {
        return getFirstItem();
    }

    public synchronized Ruby getRuby() {
        return getSecondItem();
    }
}
