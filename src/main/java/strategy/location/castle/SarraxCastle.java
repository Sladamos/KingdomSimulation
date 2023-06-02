package strategy.location.castle;

import strategy.location.settlement.SarraxSettlement;
import strategy.organism.human.Adult;
import strategy.producer.building.craftsman.present.NecklacePresentCraftsman;
import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.producer.building.craftsman.present.RingPresentCraftsman;
import strategy.producer.royal.king.King;
import strategy.producer.royal.king.SarraxKing;
import strategy.producer.royal.princess.SarraxPrincess;
import strategy.producer.royal.queen.Queen;
import strategy.producer.royal.queen.SarraxQueen;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;
import strategy.product.present.NecklacePresent;
import strategy.product.present.RingPresent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SarraxCastle implements Castle {

	private final SarraxKing king;

	private final SarraxQueen queen;

	private final SarraxPrincess<NecklacePresent, RingPresent> princess;

	private final ExecutorService executorService;

	public SarraxCastle(SarraxSettlement settlement) {
		queen = new SarraxQueen(settlement::getChild, settlement::getGrowthElixir);
		king = createKing(settlement);
		princess = new SarraxPrincess<>(king::getNecklacePresent, king::getRingPresent);
		executorService = Executors.newFixedThreadPool(3);
	}

	private SarraxKing createKing(SarraxSettlement settlement) {
		NecklacePresentCraftsman<RubyNecklace> necklacePresentCraftsman =
				new NecklacePresentCraftsman<>(settlement::getRubyNecklace, 0);
		RingPresentCraftsman<SapphireRing> ringPresentCraftsman =
				new RingPresentCraftsman<>(settlement::getSapphireRing, 0);
		return new SarraxKing(necklacePresentCraftsman, ringPresentCraftsman);
	}

	public Adult getAdult() {
		return queen.getAdult();
	}

	@Override
	public void run() {
		executorService.execute(queen);
		executorService.execute(king);
		executorService.execute(princess);
	}

	@Override
	public void terminate() {
		queen.terminate();
		king.terminate();
		princess.terminate();
		executorService.shutdownNow();
	}

	@Override
	public void attack(Castle castle) {

	}
}
