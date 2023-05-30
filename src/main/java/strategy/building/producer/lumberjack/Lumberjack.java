package strategy.building.producer.lumberjack;

import strategy.building.producer.ZeroToOneProducer;
import strategy.material.wood.Wood;

public abstract class Lumberjack<T extends Wood> extends ZeroToOneProducer<T> {

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