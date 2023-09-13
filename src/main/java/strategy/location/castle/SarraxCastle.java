package strategy.location.castle;

import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;
import strategy.location.settlement.SettlementStorageManager;
import strategy.producer.building.craftsman.present.NecklacePresentCraftsman;
import strategy.producer.building.craftsman.present.RingPresentCraftsman;
import strategy.producer.royal.king.SarraxKing;
import strategy.producer.royal.princess.SarraxPrincess;
import strategy.producer.royal.queen.SarraxQueen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxCastle implements Castle {

	private final SarraxKing king;

	private final SarraxQueen queen;

	private final SarraxPrincess<NecklacePresent, RingPresent> princess;

	private final ExecutorService executorService;

	public SarraxCastle(CastleStorageManager castleStorageManager, SettlementStorageManager settlementStorageManager, CastleConfig castleConfig) {
		queen = new SarraxQueen(settlementStorageManager.getChildStorage(), settlementStorageManager.getGrowthElixirStorage(),
				castleStorageManager.getAdultStorage(), castleConfig.getQueenConfig());

		king = createKing(castleStorageManager, settlementStorageManager, castleConfig);

		princess = new SarraxPrincess<>(castleStorageManager.getNecklacePresentStorage(),
				castleStorageManager.getRingPresentStorage(),
				castleStorageManager.getHappinessStorage(),
				castleConfig.getPrincessConfig());

		executorService = Executors.newFixedThreadPool(3);
	}

	private SarraxKing createKing(CastleStorageManager castleStorageManager, SettlementStorageManager settlementStorageManager, CastleConfig castleConfig) {
		NecklacePresentCraftsman<RubyNecklace> necklacePresentCraftsman =
				new NecklacePresentCraftsman<>(settlementStorageManager.getRubyNecklaceStorage(),
						castleStorageManager.getNecklacePresentStorage(), castleConfig.getNecklacePresentCraftsmanConfig());
		RingPresentCraftsman<SapphireRing> ringPresentCraftsman =
				new RingPresentCraftsman<>(settlementStorageManager.getSapphireRingStorage(),
						castleStorageManager.getRingPresentStorage(), castleConfig.getRingPresentCraftsmanConfig());
		return new SarraxKing(necklacePresentCraftsman, ringPresentCraftsman);
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
}
