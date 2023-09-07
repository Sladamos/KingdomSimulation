package strategy.producer.building.craftsman;

import strategy.item.Item;
import strategy.producer.OneToOneProducer;
import strategy.producer.ProducerConfig;

import java.util.function.Supplier;

public abstract class Craftsman<T extends Item, S extends Item> extends OneToOneProducer<T, S> {

	public Craftsman(Supplier<T> producer, ProducerConfig producerConfig) {
		super(producer, producerConfig);
	}
}
