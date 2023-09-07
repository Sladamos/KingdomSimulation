package strategy.producer.building.jewellery.basic.ring;

import strategy.item.mineral.gem.Sapphire;
import strategy.item.jewellery.ring.SapphireRing;

import java.util.function.Supplier;

public class SapphireRingJeweller extends strategy.producer.building.jewellery.basic.ring.RingJeweller<Sapphire, SapphireRing> {

	private static final int SAPPHIRE_RING_JEWELLER_DURABILITY = 300;

	private static final int SAPPHIRE_RING_JEWELLER_CRAFTING_SPEED = 2;

	public SapphireRingJeweller(Supplier<Sapphire> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, SAPPHIRE_RING_JEWELLER_CRAFTING_SPEED,
				SAPPHIRE_RING_JEWELLER_DURABILITY);
	}

	@Override
	protected SapphireRing createNewItem(Sapphire material) {
		return new SapphireRing();
	}

	@Override
	protected SapphireRing produceNewItem() {
		return new SapphireRing();
	}
}
