package strategy.producer.building.military.infantry;

import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.military.infantry.warrior.WarriorConfig;
import strategy.item.organism.human.Adult;
import strategy.item.weapon.meele.sword.Sword;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WarriorBarracks<T extends Sword> extends Barracks<Adult, T, Warrior> {

	private final WarriorConfig warriorConfig;

	public WarriorBarracks(OneItemStorage<Adult> firstSourceStorage, OneItemStorage<T> secondSourceStorage,
						   OneItemStorage<Warrior> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
		warriorConfig = null;
	}

	//TODO config : switch producer into barracksConfig, from them select producer config into super and infantry config
	@Override
	protected Warrior createNewItem(Adult material, T secondMaterial) {
		return new Warrior(warriorConfig);
	}
}
