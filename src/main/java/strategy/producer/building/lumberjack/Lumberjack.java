package strategy.producer.building.lumberjack;

import strategy.producer.ZeroToOneProducer;
import strategy.material.wood.Wood;
import strategy.producer.building.Building;

public abstract class Lumberjack<T extends Wood> extends ZeroToOneProducer<T>  implements Building {

    public Lumberjack(int defaultStorageSize, double producingSpeed, int durability) {
        super(defaultStorageSize, producingSpeed, durability);
    }

    public synchronized T getWood() {
        return getItem();
    }

    public synchronized int getNumberOfWoodsInStorage() {
        return getNumberOfItemsInStorage();
    }

    @Override
    protected int getProducingTime() {
        return 25000;
    }
}