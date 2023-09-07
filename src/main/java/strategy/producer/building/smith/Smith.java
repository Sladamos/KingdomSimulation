package strategy.producer.building.smith;

import strategy.item.Item;
import strategy.item.weapon.Weapon;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;
import strategy.producer.building.Building;
import strategy.storage.OneItemStorage;

public abstract class Smith <T extends Item, U extends Weapon> extends OneToOneProducer<T, U>  implements Building {

    public Smith(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}

