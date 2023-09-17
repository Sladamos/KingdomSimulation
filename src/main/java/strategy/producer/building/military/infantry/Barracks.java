package strategy.producer.building.military.infantry;

import strategy.item.organism.human.Human;
import strategy.item.weapon.meele.MeeleWeapon;
import strategy.military.MilitaryUnit;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.producer.building.military.MilitaryProducer;
import strategy.storage.OneItemStorage;

public abstract class Barracks<T extends Human, U extends MeeleWeapon>
		extends TwoToOneProducer<T, U, MilitaryUnit> implements MilitaryProducer {

	public Barracks(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage,
					OneItemStorage<MilitaryUnit> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}
}
