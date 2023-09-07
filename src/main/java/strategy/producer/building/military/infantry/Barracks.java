package strategy.producer.building.military.infantry;

import strategy.producer.TwoToOneProducer;
import strategy.producer.building.military.MilitaryProducer;
import strategy.military.infantry.InfantryUnit;
import strategy.organism.human.Human;
import strategy.item.weapon.meele.MeeleWeapon;

import java.util.function.Supplier;

public abstract class Barracks<T extends Human, U extends MeeleWeapon, V extends InfantryUnit>
		extends TwoToOneProducer<T, U, V> implements MilitaryProducer<V> {

	public Barracks(Supplier<T> firstProducer, Supplier<U> secondProducer, int defaultStorageSize, double producingSpeed, int durability) {
		super(firstProducer, secondProducer, defaultStorageSize, producingSpeed, durability);
	}

	@Override
	public synchronized V getMilitaryUnit() {
		return getItem();
	}

	@Override
	protected int getProducingTime() {
		return 45000;
	}
}
