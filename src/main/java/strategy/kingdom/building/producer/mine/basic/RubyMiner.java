package strategy.kingdom.building.producer.mine.basic;

import strategy.kingdom.material.mineral.gem.Ruby;

public class RubyMiner extends Miner<Ruby> {

    private static final int RUBY_MINER_DURABILITY = 20;

    private static final int RUBY_MINER_MINING_SPEED = 3;

    public RubyMiner(int defaultStorageSize) {
        super(defaultStorageSize, RUBY_MINER_MINING_SPEED, RUBY_MINER_DURABILITY);
    }

    @Override
    protected Ruby createNewOre() {
        return new Ruby();
    }
}
