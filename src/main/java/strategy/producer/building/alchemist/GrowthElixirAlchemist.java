package strategy.producer.building.alchemist;

import strategy.material.food.Honey;
import strategy.material.food.Milk;
import strategy.product.elixir.GrowthElixir;

import java.util.function.Supplier;

public class GrowthElixirAlchemist extends Alchemist<Milk, Honey, GrowthElixir> {

	private static final int GROWTH_ELIXIR_ALCHEMIST_DURABILITY = 100;

	private static final int GROWTH_ELIXIR_ALCHEMIST_BREWING_SPEED = 2;

	public GrowthElixirAlchemist(Supplier<Milk> firstProducer, Supplier<Honey> secondProducer, int defaultStorageSize) {
		super(firstProducer, secondProducer, defaultStorageSize,
				GROWTH_ELIXIR_ALCHEMIST_BREWING_SPEED, GROWTH_ELIXIR_ALCHEMIST_DURABILITY);
	}

	@Override
	protected GrowthElixir produceNewItem(Milk material, Honey secondMaterial) {
		return new GrowthElixir();
	}

	@Override
	protected GrowthElixir produceNewItem() {
		return new GrowthElixir();
	}
}
