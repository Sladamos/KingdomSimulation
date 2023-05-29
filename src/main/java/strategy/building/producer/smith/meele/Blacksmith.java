package strategy.building.producer.smith.meele;

import strategy.building.producer.smith.Smith;
import strategy.material.bar.Bar;
import strategy.product.weapon.meele.MeeleWeapon;

import java.util.function.Supplier;

public abstract class Blacksmith<T extends Bar, U extends MeeleWeapon> extends Smith<T, U> {

    public Blacksmith(Supplier<T> materialProducer, int defaultStorageSize, double forgingSpeed, int durability) {
        super(materialProducer, defaultStorageSize, forgingSpeed, durability);
    }

    @Override
    protected int getForgingTime() {
        return 30000;
    }
}
