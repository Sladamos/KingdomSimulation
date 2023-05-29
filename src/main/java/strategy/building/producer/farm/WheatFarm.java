package strategy.building.producer.farm;

import strategy.material.plant.Wheat;
import strategy.product.fluid.Water;

import java.util.function.Supplier;

public class WheatFarm extends Farm<Water, Wheat> {

	private static final int WHEAT_FARM_DURABILITY = 30;

	private static final int WHEAT_FARM_GROWING_SPEED = 4;

	public WheatFarm(Supplier<Water> waterProducer, int defaultStorageSize) {
		super(waterProducer, defaultStorageSize, WHEAT_FARM_GROWING_SPEED,
				WHEAT_FARM_DURABILITY);
	}

	@Override
	protected Wheat createNewPlant(Water material) {
		return new Wheat();
	}

	@Override
	protected Wheat createNewPlant() {
		return new Wheat();
	}

	@Override
	protected int getGrowingTime() {
		return 25000;
	}
}
