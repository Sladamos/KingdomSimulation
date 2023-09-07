package strategy.producer.building.craftsman.present;

import strategy.item.jewellery.ring.Ring;
import strategy.item.present.RingPresent;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class RingPresentCraftsman<T extends Ring> extends PresentCraftsman<T, RingPresent> {

	public RingPresentCraftsman(OneItemStorage<T> sourceStorage, OneItemStorage<RingPresent> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected RingPresent createNewItem(T material) {
		return new RingPresent();
	}
}
