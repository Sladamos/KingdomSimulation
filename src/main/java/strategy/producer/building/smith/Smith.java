package strategy.producer.building.smith;

import strategy.producer.OneToOneProducer;
import strategy.material.Material;
import strategy.producer.building.Building;
import strategy.product.weapon.Weapon;

import java.util.function.Supplier;

public abstract class Smith <T extends Material, U extends Weapon> extends OneToOneProducer<T, U>  implements Building {

    public Smith(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
        super(producer, defaultStorageSize, producingSpeed, durability);
    }

    public synchronized U getWeapon() {
        return getItem();
    }
}

