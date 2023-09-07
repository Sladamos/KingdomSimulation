package strategy.producer.building.jewellery.basic.necklace;

import strategy.item.mineral.gem.Ruby;
import strategy.item.jewellery.necklace.RubyNecklace;

import java.util.function.Supplier;

public class RubyNecklaceJeweller extends strategy.producer.building.jewellery.basic.necklace.NecklaceJeweller<Ruby, RubyNecklace> {

	private static final int RUBY_NECKLACE_JEWELLER_DURABILITY = 300;

	private static final int RUBY_NECKLACE_JEWELLER_CRAFTING_SPEED = 2;

	public RubyNecklaceJeweller(Supplier<Ruby> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, RUBY_NECKLACE_JEWELLER_CRAFTING_SPEED,
				RUBY_NECKLACE_JEWELLER_DURABILITY);
	}

	@Override
	protected RubyNecklace createNewItem(Ruby material) {
		return new RubyNecklace();
	}

	@Override
	protected RubyNecklace produceNewItem() {
		return new RubyNecklace();
	}
}
