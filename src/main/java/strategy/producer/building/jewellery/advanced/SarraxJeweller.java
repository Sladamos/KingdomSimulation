package strategy.producer.building.jewellery.advanced;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;
import strategy.material.mineral.gem.Sapphire;
import strategy.material.mineral.gem.Ruby;
import strategy.product.jewellery.necklace.RubyNecklace;

import java.util.function.Supplier;

public class SarraxJeweller extends TwoToTwoProducer<SapphireRingJeweller, RubyNecklaceJeweller,
		strategy.product.jewellery.ring.SapphireRing, RubyNecklace> implements Building {

	public SarraxJeweller(Supplier<Ruby> rubySupplier, Supplier<Sapphire> sapphireSupplier) {
		super(new SapphireRingJeweller(sapphireSupplier, 0),
				new RubyNecklaceJeweller(rubySupplier, 0));
	}

	public synchronized strategy.product.jewellery.ring.SapphireRing getSapphireRing() {
		return getFirstItem();
	}

	public synchronized RubyNecklace getRubyNecklace() {
		return getSecondItem();
	}
}
