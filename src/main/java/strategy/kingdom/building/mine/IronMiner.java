package strategy.kingdom.building.mine;

import strategy.kingdom.material.ore.IronOre;

public class IronMiner extends Miner<IronOre> {

    private static final int IRON_MINER_DURABILITY = 100;

    public IronMiner(int defaultStorageSize) {
        super(defaultStorageSize, 1, IRON_MINER_DURABILITY);
    }

    @Override
    protected IronOre createNewOre() {
        return new IronOre();
    }
}
