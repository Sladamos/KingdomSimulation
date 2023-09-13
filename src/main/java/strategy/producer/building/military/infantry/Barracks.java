package strategy.producer.building.military.infantry;

import strategy.military.infantry.InfantryUnit;
import strategy.item.organism.human.Human;
import strategy.item.weapon.meele.MeeleWeapon;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.producer.building.military.MilitaryProducer;
import strategy.storage.OneItemStorage;

public abstract class Barracks<T extends Human, U extends MeeleWeapon>
		extends TwoToOneProducer<T, U, InfantryUnit> implements MilitaryProducer {

	public Barracks(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage,
	                OneItemStorage<InfantryUnit> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}
}
