package strategy.producer.building.military.infantry;

import strategy.initializer.military.RandomWarriorsInitializer;
import strategy.initializer.military.WarriorsInitializer;
import strategy.military.infantry.InfantryUnit;
import strategy.military.infantry.warrior.Warrior;
import strategy.military.infantry.warrior.WarriorConfig;
import strategy.item.organism.human.Adult;
import strategy.item.weapon.meele.sword.Sword;
import strategy.storage.OneItemStorage;

public class WarriorBarracks<T extends Sword> extends Barracks<Adult, T> {

	private final WarriorConfig warriorConfig;
	private final WarriorsInitializer warriorsInitializer;

	public WarriorBarracks(OneItemStorage<Adult> firstSourceStorage, OneItemStorage<T> secondSourceStorage,
	                       OneItemStorage<InfantryUnit> destinationStorage, BarracksConfig<WarriorConfig> barracksConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, barracksConfig.getMilitaryProducerConfig());
		warriorConfig = barracksConfig.getInfantryConfig();
		warriorsInitializer = new RandomWarriorsInitializer();
	}

	@Override
	protected Warrior createNewItem(Adult material, T secondMaterial) {
		return warriorsInitializer.createWarrior(warriorConfig);
	}
}
