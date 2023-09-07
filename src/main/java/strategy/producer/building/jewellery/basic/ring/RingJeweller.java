package strategy.producer.building.jewellery.basic.ring;

import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.item.Item;
import strategy.item.jewellery.ring.Ring;

import java.util.function.Supplier;

public abstract class RingJeweller<T extends Item, U extends Ring> extends Jeweller<T, U> {

	public RingJeweller(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
		super(materialProducer, defaultStorageSize, craftingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 40000;
	}
}
