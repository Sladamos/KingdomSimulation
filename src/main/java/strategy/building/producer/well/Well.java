package strategy.building.producer.well;

import strategy.building.producer.OneToOneProducer;
import strategy.product.Product;
import strategy.product.tool.bucket.Bucket;

import java.util.function.Supplier;

public abstract class Well<T extends Bucket, U extends Product> extends OneToOneProducer<T, U> {

	public Well(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getItem() {
		return super.getItem();
	}
}

