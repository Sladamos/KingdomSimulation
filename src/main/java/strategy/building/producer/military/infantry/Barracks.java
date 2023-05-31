package strategy.building.producer.military.infantry;

import strategy.building.producer.TwoToOneProducer;
import strategy.building.producer.military.MilitaryProducer;
import strategy.military.infantry.InfantryUnit;
import strategy.organism.human.Human;
import strategy.product.weapon.meele.MeeleWeapon;

import java.util.function.Supplier;

public abstract class Barracks<T extends Human, U extends MeeleWeapon, V extends InfantryUnit>
		extends TwoToOneProducer<T, U, V> implements MilitaryProducer {

	public Barracks(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 45000;
	}
}
