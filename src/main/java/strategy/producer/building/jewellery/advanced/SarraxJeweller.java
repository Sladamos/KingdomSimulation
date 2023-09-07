package strategy.producer.building.jewellery.advanced;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;
import strategy.item.mineral.gem.Sapphire;
import strategy.item.mineral.gem.Ruby;
import strategy.item.jewellery.necklace.RubyNecklace;

import java.util.function.Supplier;

public class SarraxJeweller extends TwoToTwoProducer<SapphireRingJeweller, RubyNecklaceJeweller,
		strategy.item.jewellery.ring.SapphireRing, RubyNecklace> implements Building {

	public SarraxJeweller(Supplier<Ruby> rubySupplier, Supplier<Sapphire> sapphireSupplier) {
		super(new SapphireRingJeweller(sapphireSupplier, 0),
				new RubyNecklaceJeweller(rubySupplier, 0));
	}

	public synchronized strategy.item.jewellery.ring.SapphireRing getSapphireRing() {
		return getFirstItem();
	}

	public synchronized RubyNecklace getRubyNecklace() {
		return getSecondItem();
	}
}
