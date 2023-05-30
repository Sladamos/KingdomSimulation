package strategy.building.producer.miner.basic;

import strategy.building.producer.ZeroToOneProducer;
import strategy.material.mineral.Mineral;


public abstract class Miner <T extends Mineral> extends ZeroToOneProducer<T> {

    public Miner(int defaultStorageSize, double producingSpeed, int durability) {
        super(defaultStorageSize, producingSpeed, durability);
    }

    public synchronized T getMineral() {
        return getItem();
    }

    public synchronized int getNumberOfMineralsInStorage() {
        return getNumberOfItemsInStorage();
    }

    @Override
    public int getProducingTime() {
        return 15000;
    }
}
