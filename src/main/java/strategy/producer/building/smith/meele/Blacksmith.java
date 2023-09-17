package strategy.producer.building.smith.meele;

import strategy.item.bar.Bar;
import strategy.item.weapon.meele.MeeleWeapon;
import strategy.producer.ProducerConfig;
import strategy.producer.building.smith.Smith;
import strategy.storage.OneItemStorage;

public abstract class Blacksmith<T extends Bar, U extends MeeleWeapon> extends Smith<T, U> {

    public Blacksmith(OneItemStorage<T> sourceStorage, OneItemStorage<U> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }
}
