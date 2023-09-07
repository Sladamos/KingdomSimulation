package strategy.producer.building.craftsman.present;

import strategy.item.Item;
import strategy.producer.ProducerConfig;
import strategy.producer.building.craftsman.Craftsman;
import strategy.item.present.Present;

import java.util.function.Supplier;

public abstract class PresentCraftsman<T extends Item, S extends Present> extends Craftsman<T, S> {

	public PresentCraftsman(Supplier<T> producer, ProducerConfig producerConfig) {
		super(producer, producerConfig);
	}
}
