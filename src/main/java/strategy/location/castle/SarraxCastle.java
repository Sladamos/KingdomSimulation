package strategy.location.castle;

import strategy.location.settlement.SarraxSettlement;
import strategy.producer.building.craftsman.present.NecklacePresentCraftsman;
import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.producer.building.craftsman.present.RingPresentCraftsman;
import strategy.producer.royal.king.King;
import strategy.producer.royal.king.SarraxKing;
import strategy.producer.royal.queen.Queen;
import strategy.producer.royal.queen.SarraxQueen;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;
import strategy.product.present.NecklacePresent;

public class SarraxCastle implements Castle {

	private final SarraxKing king;

	private final SarraxQueen queen;

	public SarraxCastle(SarraxSettlement settlement) {
		queen = new SarraxQueen(settlement::getChild, settlement::getGrowthElixir);
		king = createKing(settlement);
	}

	private SarraxKing createKing(SarraxSettlement settlement) {
		NecklacePresentCraftsman<RubyNecklace> necklacePresentCraftsman =
				new NecklacePresentCraftsman<>(settlement::getRubyNecklace, 0);
		RingPresentCraftsman<SapphireRing> ringPresentCraftsman =
				new RingPresentCraftsman<>(settlement::getSapphireRing, 0);
		return new SarraxKing(necklacePresentCraftsman, ringPresentCraftsman);
	}
}
