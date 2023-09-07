package strategy.location.castle;

import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.military.infantry.HumanInfantryUnit;
import strategy.item.military.infantry.InfantryGeneral;
import strategy.item.military.infantry.InfantryGeneralImpl;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;
import strategy.producer.building.craftsman.present.NecklacePresentCraftsman;
import strategy.producer.building.craftsman.present.RingPresentCraftsman;
import strategy.producer.royal.king.SarraxKing;
import strategy.producer.royal.princess.SarraxPrincess;
import strategy.producer.royal.queen.SarraxQueen;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxCastle<T extends HumanInfantryUnit> implements Castle<T> {

	private final SarraxKing king;

	private final SarraxQueen queen;

	private final SarraxPrincess<NecklacePresent, RingPresent> princess;

	private final InfantryGeneral<T> warriorsGeneral;

	private final ExecutorService executorService;

	public SarraxCastle(CastleStorageManager castleStorageManager) {
		queen = new SarraxQueen(null, null, castleStorageManager.getAdultStorage(), null);
		king = createKing(castleStorageManager);
		princess = new SarraxPrincess<>(castleStorageManager.getNecklacePresentStorage(),
				castleStorageManager.getRingPresentStorage(),
				castleStorageManager.getHappinessStorage(),
				null);
		warriorsGeneral = new InfantryGeneralImpl<>(castleStorageManager.getHappinessStorage(), null, null);
		executorService = Executors.newFixedThreadPool(5);
	}

	private SarraxKing createKing(CastleStorageManager castleStorageManager) {
		NecklacePresentCraftsman<RubyNecklace> necklacePresentCraftsman =
				new NecklacePresentCraftsman<>(null, castleStorageManager.getNecklacePresentStorage(), null);
		RingPresentCraftsman<SapphireRing> ringPresentCraftsman =
				new RingPresentCraftsman<>(null, castleStorageManager.getRingPresentStorage(), null);
		return new SarraxKing(necklacePresentCraftsman, ringPresentCraftsman);
	}

	@Override
	public void run() {
		executorService.execute(queen);
		executorService.execute(king);
		executorService.execute(princess);
		executorService.execute(warriorsGeneral::runUnitsConusmer);
		executorService.execute(warriorsGeneral::runHappinessConusmer);
	}

	@Override
	public void terminate() {
		queen.terminate();
		king.terminate();
		princess.terminate();
		warriorsGeneral.terminate();
		executorService.shutdownNow();
	}

	@Override
	public void attack(Castle castle) {
		Collection<Integer> totalDamage = warriorsGeneral.getArmyDamage();
		castle.receiveDamage(totalDamage);
	}

	@Override
	public void receiveDamage(Collection<Integer> damage) {
		warriorsGeneral.receiveDamage(damage);
	}

	@Override
	public void addInfantry(Collection<T> infantryUnits) {
		warriorsGeneral.addInfantryUnits(infantryUnits);
	}
}
