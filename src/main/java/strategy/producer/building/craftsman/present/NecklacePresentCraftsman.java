package strategy.producer.building.craftsman.present;

import strategy.product.jewellery.necklace.Necklace;
import strategy.product.present.NecklacePresent;

import java.util.function.Supplier;

public class NecklacePresentCraftsman<T extends Necklace> extends PresentCraftsman<T, NecklacePresent> {

	public NecklacePresentCraftsman(Supplier<T> producer, int defaultStorageSize) {
		super(producer, defaultStorageSize);
	}

	@Override
	protected NecklacePresent produceNewItem() {
		return new NecklacePresent();
	}
}
