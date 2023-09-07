package strategy.producer.building.house;

import strategy.item.coin.Coin;
import strategy.item.food.baking.bread.Bread;
import strategy.item.organism.human.Child;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class ChildHouse<T extends Coin, U extends Bread> extends House<T, U, Child> {

	public ChildHouse(OneItemStorage<T> firstSourceStorage, OneItemStorage<U> secondSourceStorage, OneItemStorage<Child> destinationStorage, ProducerConfig producerConfig) {
		super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected Child createNewItem(T material, U secondMaterial) {
		return new Child(0, 100);
	}
}
