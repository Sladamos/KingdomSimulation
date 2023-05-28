package strategy.kingdom.building.producer.weapon.meele;

import strategy.kingdom.building.producer.weapon.Smith;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.product.weapon.meele.MeeleWeapon;

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
