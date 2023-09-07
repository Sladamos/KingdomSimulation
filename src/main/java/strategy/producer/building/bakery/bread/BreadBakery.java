package strategy.producer.building.bakery.bread;

import strategy.item.flour.Flour;
import strategy.item.food.baking.bread.Bread;
import strategy.item.mineral.Salt;
import strategy.producer.ProducerConfig;
import strategy.producer.building.bakery.Bakery;
import strategy.storage.OneItemStorage;

public abstract class BreadBakery<T extends Flour, U extends Salt, V extends Bread> extends Bakery<T, U, V> {

    public BreadBakery(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<V> destinationStorage, ProducerConfig producerConfig) {
        super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
    }
}
