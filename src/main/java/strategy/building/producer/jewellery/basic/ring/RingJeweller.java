package strategy.building.producer.jewellery.basic.ring;

import strategy.building.producer.jewellery.Jeweller;
import strategy.material.Material;
import strategy.product.jewellery.ring.Ring;

import java.util.function.Supplier;

public abstract class RingJeweller<T extends Material, U extends Ring> extends Jeweller<T, U> {

	public RingJeweller(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
		super(materialProducer, defaultStorageSize, craftingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 40000;
	}
}
