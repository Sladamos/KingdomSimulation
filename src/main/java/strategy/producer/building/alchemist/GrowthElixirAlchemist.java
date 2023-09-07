package strategy.producer.building.alchemist;

import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.elixir.GrowthElixir;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

import java.util.function.Supplier;

public class GrowthElixirAlchemist extends Alchemist<Milk, Honey, GrowthElixir> {

	public GrowthElixirAlchemist(OneItemStorage<Milk> firstSourceStorage, OneItemStorage<Honey> secondSourceStorage,
								 OneItemStorage<GrowthElixir> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected GrowthElixir createNewItem(Milk firstMaterial, Honey secondMaterial) {
		return new GrowthElixir();
	}
