package strategy.producer.building.lumberjack;

import strategy.material.wood.Mahogany;

public class MahoganyLumberjack extends Lumberjack<Mahogany> {

    private static final int MAHOGANY_LUMBERJACK_DURABILITY = 100;

    private static final int IRON_MINER_MINING_SPEED = 5;

    public MahoganyLumberjack(int defaultStorageSize) {
        super(defaultStorageSize, IRON_MINER_MINING_SPEED, MAHOGANY_LUMBERJACK_DURABILITY);
    }

    @Override
    protected Mahogany produceNewItem() {
        return new Mahogany();
    }
}
