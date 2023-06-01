package strategy.producer.royal.king;

import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;
import strategy.product.present.NecklacePresent;
import strategy.product.present.RingPresent;

public class SarraxKing extends King<PresentCraftsman<RubyNecklace, NecklacePresent>,
		PresentCraftsman<SapphireRing, RingPresent>, NecklacePresent, RingPresent> {

	public SarraxKing(PresentCraftsman<RubyNecklace, NecklacePresent> firstProducer,
	                  PresentCraftsman<SapphireRing, RingPresent> secondProducer) {
		super(firstProducer, secondProducer);
		/*
		var firstCraftsman = new PresentCraftsman<RubyNecklace, NecklacePresent>(firstProducer::getItem, 0) {
			@Override
			protected NecklacePresent produceNewItem(RubyNecklace necklace) {
				return new NecklacePresent();
			}

			@Override
			protected NecklacePresent produceNewItem() {
				return new NecklacePresent();
			}
		};
		
		var secondCraftsman =  new PresentCraftsman<SapphireRing, RingPresent>(secondProducer::getItem, 0) {
			@Override
			protected RingPresent produceNewItem(SapphireRing necklace) {
				return new RingPresent();
			}

			@Override
			protected RingPresent produceNewItem() {
				return new RingPresent();
			}
		};

		 */
	}
}
