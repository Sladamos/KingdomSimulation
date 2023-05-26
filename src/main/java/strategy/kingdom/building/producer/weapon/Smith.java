package strategy.kingdom.building.producer.weapon;

import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.material.Material;
import strategy.kingdom.product.weapon.Weapon;

public interface Smith <T extends Weapon, S extends Material> extends Producer {
}
