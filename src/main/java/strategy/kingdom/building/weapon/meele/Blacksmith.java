package strategy.kingdom.building.weapon.meele;

import strategy.kingdom.building.weapon.Smith;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.product.weapon.meele.MeeleWeapon;

public interface Blacksmith<T extends MeeleWeapon, S extends Bar> extends Smith<T, S> {
}
