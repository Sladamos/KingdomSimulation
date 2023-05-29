package strategy.building.producer.well.basic;

import strategy.building.producer.well.Well;
import strategy.product.coin.GoldenCoin;
import strategy.product.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class GoldenCoinWell extends Well<WoodenBucket, GoldenCoin> {

	private static final int GOLDEN_COIN_WELL_DURABILITY = 90;

	private static final int GOLDEN_COIN_WELL_EXTRACTING_SPEED = 1;

	public GoldenCoinWell(Supplier<WoodenBucket> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, GOLDEN_COIN_WELL_EXTRACTING_SPEED,
				GOLDEN_COIN_WELL_DURABILITY);
	}

	@Override
	protected GoldenCoin extractNewItem(WoodenBucket material) {
		return new GoldenCoin();
	}

	@Override
	protected GoldenCoin extractNewItem() {
		return new GoldenCoin();
	}

	@Override
	protected int getExtractingTime() {
		return 7000;
	}
}
