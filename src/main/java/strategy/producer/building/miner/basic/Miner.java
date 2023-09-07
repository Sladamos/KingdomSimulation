package strategy.producer.building.miner.basic;

import strategy.producer.ZeroToOneProducer;
import strategy.item.mineral.Mineral;
import strategy.producer.building.Building;

public abstract class Miner <T extends Mineral> extends ZeroToOneProducer<T>  implements Building {

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
