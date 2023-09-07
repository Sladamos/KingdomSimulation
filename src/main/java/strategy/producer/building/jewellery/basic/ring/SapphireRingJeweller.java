package strategy.producer.building.jewellery.basic.ring;

import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.mineral.gem.Sapphire;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class SapphireRingJeweller extends strategy.producer.building.jewellery.basic.ring.RingJeweller<Sapphire, SapphireRing> {

	public SapphireRingJeweller(OneItemStorage<Sapphire> sourceStorage, OneItemStorage<SapphireRing> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected SapphireRing createNewItem(Sapphire material) {
		return new SapphireRing();
	}
}
