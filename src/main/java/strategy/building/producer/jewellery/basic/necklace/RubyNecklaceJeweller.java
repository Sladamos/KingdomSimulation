package strategy.building.producer.jewellery.basic.necklace;

import strategy.material.mineral.gem.Ruby;
import strategy.product.jewellery.necklace.RubyNecklace;

import java.util.function.Supplier;

public class RubyNecklaceJeweller extends strategy.building.producer.jewellery.basic.necklace.NecklaceJeweller<Ruby, RubyNecklace> {

	private static final int RUBY_NECKLACE_JEWELLER_DURABILITY = 300;

	private static final int RUBY_NECKLACE_JEWELLER_CRAFTING_SPEED = 2;

	public RubyNecklaceJeweller(Supplier<Ruby> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, RUBY_NECKLACE_JEWELLER_CRAFTING_SPEED,
				RUBY_NECKLACE_JEWELLER_DURABILITY);
	}

	@Override
	protected RubyNecklace produceNewItem(Ruby material) {
		return new RubyNecklace();
	}

	@Override
	protected RubyNecklace produceNewItem() {
		return new RubyNecklace();
	}
}
