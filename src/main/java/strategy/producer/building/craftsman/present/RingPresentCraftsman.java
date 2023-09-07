package strategy.producer.building.craftsman.present;

import strategy.item.jewellery.ring.Ring;
import strategy.item.present.RingPresent;

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
