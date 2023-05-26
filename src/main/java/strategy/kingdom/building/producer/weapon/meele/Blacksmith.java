package strategy.kingdom.building.producer.weapon.meele;

import strategy.kingdom.building.producer.weapon.Smith;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.product.weapon.meele.MeeleWeapon;

public abstract class Blacksmith<T extends MeeleWeapon, S extends Bar> implements Smith<T, S> {

    @Override
    public void run() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void dealDamage(int damage) {

    }

    @Override
    public int getDurability() {
        return 0;
    }
}
