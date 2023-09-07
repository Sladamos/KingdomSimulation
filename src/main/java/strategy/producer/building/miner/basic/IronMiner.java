package strategy.producer.building.miner.basic;

import strategy.item.mineral.ore.IronOre;

public class IronMiner extends Miner<IronOre> {

    private static final int IRON_MINER_DURABILITY = 100;

    private static final int IRON_MINER_MINING_SPEED = 5;

    public IronMiner(int defaultStorageSize) {
        super(defaultStorageSize, IRON_MINER_MINING_SPEED, IRON_MINER_DURABILITY);
    }

    @Override
    protected IronOre produceNewItem() {
        return new IronOre();
    }
}
