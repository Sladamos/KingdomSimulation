package strategy.building.producer.well;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.OneToOneProducer;
import strategy.building.producer.Producer;
import strategy.product.Product;
import strategy.product.tool.bucket.Bucket;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Well<T extends Bucket, U extends Product> extends OneToOneProducer<T, U> {

	public Well(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getItem() {
		return super.getItem();
	}
}

