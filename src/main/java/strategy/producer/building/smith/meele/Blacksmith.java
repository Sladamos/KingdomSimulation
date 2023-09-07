package strategy.producer.building.smith.meele;

import strategy.producer.building.smith.Smith;
import strategy.item.bar.Bar;
import strategy.item.weapon.meele.MeeleWeapon;

import java.util.function.Supplier;

public abstract class Blacksmith<T extends Bar, U extends MeeleWeapon> extends Smith<T, U> {

    public Blacksmith(Supplier<T> materialProducer, int defaultStorageSize, double forgingSpeed, int durability) {
        super(materialProducer, defaultStorageSize, forgingSpeed, durability);
    }

    @Override
    protected int getProducingTime() {
        return 30000;
    }
}
