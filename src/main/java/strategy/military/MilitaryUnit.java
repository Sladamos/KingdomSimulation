package strategy.military;

import strategy.item.Item;
import strategy.mechanism.fight.Fightable;

public interface MilitaryUnit extends Fightable, Item {
	void setDamageModifier(int damageModifier);
}
