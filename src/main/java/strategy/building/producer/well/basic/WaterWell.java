package strategy.building.producer.well.basic;

import strategy.building.producer.well.Well;
import strategy.product.fluid.Water;
import strategy.product.tool.bucket.IronBucket;

import java.util.function.Supplier;

public class WaterWell extends Well<IronBucket, Water> {

	private static final int WATER_WELL_DURABILITY = 50;

	private static final int WATER_WELL_EXTRACTING_SPEED = 2;

	public WaterWell(Supplier<IronBucket> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, WATER_WELL_EXTRACTING_SPEED,
				WATER_WELL_DURABILITY);
	}

	@Override
	protected Water extractNewItem(IronBucket material) {
		return new Water();
	}

	@Override
	protected Water extractNewItem() {
		return new Water();
	}

	@Override
	protected int getExtractingTime() {
		return 5000;
	}
}
