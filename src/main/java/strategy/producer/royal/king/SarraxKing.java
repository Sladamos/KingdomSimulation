package strategy.producer.royal.king;

import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;
import strategy.product.present.NecklacePresent;
import strategy.product.present.RingPresent;

public class SarraxKing extends
		King<RubyNecklaceJeweller, SapphireRingJeweller, NecklacePresent, RingPresent> {

	public SarraxKing(RubyNecklaceJeweller firstProducer, SapphireRingJeweller secondProducer) {
		super(firstProducer, secondProducer);
	}
}
