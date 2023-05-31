package strategy.producer.building.jewellery.basic.necklace;

import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.material.Material;
import strategy.product.jewellery.necklace.Necklace;

import java.util.function.Supplier;

public abstract class NecklaceJeweller<T extends Material, U extends Necklace> extends Jeweller<T, U> {

	public NecklaceJeweller(Supplier<T> materialProducer, int defaultStorageSize, double craftingSpeed, int durability) {
		super(materialProducer, defaultStorageSize, craftingSpeed, durability);
	}

	@Override
	protected int getProducingTime() {
		return 50000;
	}
}
