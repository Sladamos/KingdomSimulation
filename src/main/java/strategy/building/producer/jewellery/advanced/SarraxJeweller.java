package strategy.building.producer.jewellery.advanced;

import strategy.building.producer.TwoToTwoProducer;
import strategy.building.producer.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.building.producer.jewellery.basic.ring.SapphireRingJeweller;
import strategy.material.mineral.gem.Sapphire;
import strategy.material.mineral.gem.Ruby;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;

import java.util.function.Supplier;

public class SarraxJeweller extends TwoToTwoProducer<SapphireRingJeweller, RubyNecklaceJeweller,
		SapphireRing, RubyNecklace> {

	public SarraxJeweller(Supplier<Ruby> rubySupplier, Supplier<Sapphire> sapphireSupplier) {
		super(new SapphireRingJeweller(sapphireSupplier, 3),
				new RubyNecklaceJeweller(rubySupplier, 2));
	}

	public synchronized SapphireRing getSapphireRing() {
		return getFirstItem();
	}

	public synchronized RubyNecklace getRubyNecklace() {
		return getSecondItem();
	}
}
