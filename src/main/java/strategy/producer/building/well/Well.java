package strategy.producer.building.well;

import strategy.producer.OneToOneProducer;
import strategy.producer.building.Building;
import strategy.item.Item;
import strategy.item.tool.bucket.Bucket;

import java.util.function.Supplier;

public abstract class Well<T extends Bucket, U extends Item> extends OneToOneProducer<T, U> implements Building {

	public Well(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getItem() {
		return super.getItem();
	}
}

