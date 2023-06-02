package strategy.producer.building.well.advanced;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.well.basic.GoldenCoinWell;
import strategy.producer.building.well.basic.WaterWell;
import strategy.product.coin.GoldenCoin;
import strategy.product.fluid.Water;
import strategy.product.tool.bucket.IronBucket;
import strategy.product.tool.bucket.WoodenBucket;

import java.util.function.Supplier;

public class SarraxWell extends TwoToTwoProducer<WaterWell, GoldenCoinWell, Water, GoldenCoin> {

	public SarraxWell(Supplier<WoodenBucket> woodenBucketSupplier, Supplier<IronBucket> ironBucketSupplier) {
		super(new WaterWell(ironBucketSupplier, 0), new GoldenCoinWell(woodenBucketSupplier, 0));
	}

	public synchronized Water getWater() {
		return getFirstItem();
	}

	public synchronized GoldenCoin getGoldenCoin() {
		return getSecondItem();
	}
}
