package strategy.producer.building.jewellery.basic;

import strategy.producer.OneToOneProducer;
import strategy.item.Item;
import strategy.producer.building.Building;
import strategy.item.jewellery.Jewellery;

import java.util.function.Supplier;

public abstract class Jeweller<T extends Item, U extends Jewellery> extends OneToOneProducer<T, U>  implements Building {

	public Jeweller(Supplier<T> producer, int defaultStorageSize, double producingSpeed, int durability) {
		super(producer, defaultStorageSize, producingSpeed, durability);
	}

	public synchronized U getJewellery() {
		return getItem();
	}
}


