package strategy.producer.building.miner.advanced;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.miner.basic.IronMiner;
import strategy.producer.building.miner.basic.RubyMiner;
import strategy.material.mineral.gem.Ruby;
import strategy.material.mineral.ore.IronOre;

public class SarraxMiner extends TwoToTwoProducer<IronMiner, RubyMiner, IronOre, Ruby> implements Building {

    public SarraxMiner() {
        super(new IronMiner(0), new RubyMiner(0));
    }

    public synchronized IronOre getIronOre() {
        return getFirstItem();
    }

    public synchronized Ruby getRuby() {
        return getSecondItem();
    }
}
