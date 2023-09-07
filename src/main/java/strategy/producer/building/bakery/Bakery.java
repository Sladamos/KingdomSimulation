package strategy.producer.building.bakery;

import strategy.item.Item;
import strategy.item.flour.Flour;
import strategy.item.food.baking.Baking;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Bakery<T extends Flour, U extends Item, V extends Baking> extends TwoToOneProducer<T, U, V>
        implements Building {

    public Bakery(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<V> destinationStorage, ProducerConfig producerConfig) {
        super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
    }
}