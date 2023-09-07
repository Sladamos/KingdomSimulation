package strategy.producer.building.jewellery.advanced;

import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.Building;
import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;

public class SarraxJeweller extends TwoToTwoProducer<SapphireRingJeweller, RubyNecklaceJeweller,
		SapphireRing, RubyNecklace> implements Building {

	public SarraxJeweller(SapphireRingJeweller firstProducer, RubyNecklaceJeweller secondProducer) {
		super(firstProducer, secondProducer);
	}
}
