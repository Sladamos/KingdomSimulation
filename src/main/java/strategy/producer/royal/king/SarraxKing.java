package strategy.producer.royal.king;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;
import strategy.product.present.NecklacePresent;
import strategy.product.present.RingPresent;

public class SarraxKing extends TwoToTwoProducer<PresentCraftsman<RubyNecklace, NecklacePresent>,
		PresentCraftsman<SapphireRing, RingPresent>, NecklacePresent, RingPresent> implements King {

	public SarraxKing(PresentCraftsman<RubyNecklace, NecklacePresent> firstProducer,
	                  PresentCraftsman<SapphireRing, RingPresent> secondProducer) {
		super(firstProducer, secondProducer);
	}

	public synchronized NecklacePresent getNecklacePresent() {
		return getFirstItem();
	}

	public synchronized RingPresent getRingPresent() {
		return getSecondItem();
	}
}
