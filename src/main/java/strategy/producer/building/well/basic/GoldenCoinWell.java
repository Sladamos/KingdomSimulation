package strategy.producer.building.well.basic;

import strategy.producer.building.well.Well;
import strategy.item.coin.GoldenCoin;
import strategy.item.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class GoldenCoinWell extends Well<WoodenBucket, GoldenCoin> {

	private static final int GOLDEN_COIN_WELL_DURABILITY = 90;

	private static final int GOLDEN_COIN_WELL_EXTRACTING_SPEED = 1;

	public GoldenCoinWell(Supplier<WoodenBucket> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, GOLDEN_COIN_WELL_EXTRACTING_SPEED,
				GOLDEN_COIN_WELL_DURABILITY);
	}

	@Override
	protected GoldenCoin createNewItem(WoodenBucket material) {
		return new GoldenCoin();
	}

	@Override
	protected GoldenCoin produceNewItem() {
		return new GoldenCoin();
	}

	@Override
	protected int getProducingTime() {
		return 7000;
	}
}
