package strategy.military;

import strategy.item.Item;
import strategy.military.mechanism.fight.Fightable;

public interface MilitaryUnit extends Fightable, Item {
	void setDamageModifier(int damageModifier);
}
