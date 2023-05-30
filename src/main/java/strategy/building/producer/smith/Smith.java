package strategy.building.producer.smith;

import strategy.building.producer.OneToOneProducer;
import strategy.material.Material;
import strategy.product.weapon.Weapon;

import java.util.function.Supplier;

public abstract class Smith <T extends Material, U extends Weapon> extends OneToOneProducer<T, U> {

    public Smith(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getWeapon() {
        return getItem();
    }
}

