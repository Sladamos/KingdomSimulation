package strategy.producer.building.craftsman.present;

import strategy.product.jewellery.ring.Ring;
import strategy.product.present.RingPresent;

import java.util.function.Supplier;

public class RingPresentCraftsman<T extends Ring> extends PresentCraftsman<T, RingPresent> {

	public RingPresentCraftsman(Supplier<T> producer, int defaultStorageSize) {
		super(producer, defaultStorageSize);
	}

	@Override
	protected RingPresent produceNewItem() {
		return new RingPresent();
	}
}
