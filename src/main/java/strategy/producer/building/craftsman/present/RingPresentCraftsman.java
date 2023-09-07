package strategy.producer.building.craftsman.present;

import strategy.item.jewellery.ring.Ring;
import strategy.item.present.RingPresent;
import strategy.producer.ProducerConfig;

import java.util.function.Supplier;

public class RingPresentCraftsman<T extends Ring> extends PresentCraftsman<T, RingPresent> {

	public RingPresentCraftsman(Supplier<T> producer, ProducerConfig producerConfig) {
		super(producer, producerConfig);
	}

	@Override
	protected RingPresent createNewItem(T material) {
		return new RingPresent();
	}
}
