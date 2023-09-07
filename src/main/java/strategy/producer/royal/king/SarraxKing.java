package strategy.producer.royal.king;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;

public class SarraxKing extends TwoToTwoProducer<PresentCraftsman<RubyNecklace, NecklacePresent>,
		PresentCraftsman<SapphireRing, RingPresent>, NecklacePresent, RingPresent> implements King {
	public SarraxKing(PresentCraftsman<RubyNecklace, NecklacePresent> firstProducer, PresentCraftsman<SapphireRing, RingPresent> secondProducer) {
		super(firstProducer, secondProducer);
	}
}
