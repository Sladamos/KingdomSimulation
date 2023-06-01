package strategy.producer.building.craftsman.present;

import strategy.producer.building.craftsman.Craftsman;
import strategy.product.present.Present;

import java.util.function.Supplier;

public abstract class PresentCraftsman<T, S extends Present> extends Craftsman<T, S> {

	private static final int CRAFTSMAN_DURABILITY = 100;

	private static final int CRAFTSMAN_CRAFTING_SPEED = 5;

	public PresentCraftsman(Supplier<T> producer, int defaultStorageSize) {
		super(producer, defaultStorageSize, CRAFTSMAN_CRAFTING_SPEED, CRAFTSMAN_DURABILITY);
	}

	@Override
	protected int getProducingTime() {
		return 35000;
	}
}
