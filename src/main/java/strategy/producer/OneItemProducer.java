package strategy.producer;

import strategy.item.Item;

public interface OneItemProducer<T extends Item> extends Producer {
	T getItem();
}
