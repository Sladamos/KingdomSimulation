package strategy.producer.building.military.infantry;

import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.organism.human.Adult;
import strategy.item.weapon.meele.sword.Sword;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WarriorBarracks<T extends Sword> extends Barracks<Adult, T, Warrior> {

	public WarriorBarracks(OneItemStorage<Adult> firstSourceStorage, OneItemStorage<T> secondSourceStorage, OneItemStorage<Warrior> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}

	//TODO config
	@Override
	protected Warrior createNewItem(Adult material, T secondMaterial) {
		return new Warrior(10, 5);
	}
}
