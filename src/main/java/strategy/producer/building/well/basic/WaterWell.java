package strategy.producer.building.well.basic;

import strategy.producer.building.well.Well;
import strategy.item.fluid.Water;
import strategy.item.tool.bucket.IronBucket;

import java.util.function.Supplier;

public class WaterWell extends Well<IronBucket, Water> {

	private static final int WATER_WELL_DURABILITY = 50;

	private static final int WATER_WELL_EXTRACTING_SPEED = 2;

	public WaterWell(Supplier<IronBucket> materialProducer, int defaultStorageSize) {
		super(materialProducer, defaultStorageSize, WATER_WELL_EXTRACTING_SPEED,
				WATER_WELL_DURABILITY);
	}

	@Override
	protected Water produceNewItem(IronBucket material) {
		return new Water();
	}

	@Override
	protected Water produceNewItem() {
		return new Water();
	}

	@Override
	protected int getProducingTime() {
		return 5000;
	}
}
