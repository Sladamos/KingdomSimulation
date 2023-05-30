package strategy.building.producer.jewellery;

import lombok.Getter;
import lombok.Synchronized;
import strategy.building.exceptions.BuildingDestroyedException;
import strategy.building.exceptions.IncorrectDamageException;
import strategy.building.exceptions.IncorrectStorageException;
import strategy.building.producer.OneToOneProducer;
import strategy.building.producer.Producer;
import strategy.material.Material;
import strategy.product.jewellery.Jewellery;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

public abstract class Jeweller<T extends Material, U extends Jewellery> extends OneToOneProducer<T, U> {

	public Jeweller(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getJewellery() {
		return getItem();
	}
}


