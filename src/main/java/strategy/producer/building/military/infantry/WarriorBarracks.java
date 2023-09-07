package strategy.producer.building.military.infantry;

import strategy.military.infantry.warrior.Warrior;
import strategy.organism.human.Adult;
import strategy.item.weapon.meele.sword.Sword;

import java.util.function.Supplier;

public class WarriorBarracks<T extends Sword> extends Barracks<Adult, T, Warrior> {

	private static final int WARRIOR_BARRACKS_DURABILITY = 500;

	private static final int WARRIOR_BARRACKS_TRAINING_SPEED = 5;

	public WarriorBarracks(Supplier<Adult> firstProducer, Supplier<T> secondProducer, int defaultStorageSize) {
		super(firstProducer, secondProducer, defaultStorageSize, WARRIOR_BARRACKS_TRAINING_SPEED,
				WARRIOR_BARRACKS_DURABILITY);
	}

	@Override
	protected Warrior produceNewItem(Adult material, T secondMaterial) {
		return new Warrior(10, 5);
	}

	@Override
	protected Warrior produceNewItem() {
		return new Warrior(15, 5);
	}
}
