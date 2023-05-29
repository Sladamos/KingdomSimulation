package strategy.building.producer.well.basic;

import strategy.building.producer.well.Well;
import strategy.product.coin.GoldenCoin;
import strategy.product.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class GoldenCoinWell extends Well<WoodenBucket, GoldenCoin> {

	public GoldenCoinWell(Supplier<WoodenBucket> materialProducer, int defaultStorageSize, double extractingSpeed, int durability) {
		super(materialProducer, defaultStorageSize, extractingSpeed, durability);
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
