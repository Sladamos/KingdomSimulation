package strategy.building.producer;

public interface OneItemProducer<T> extends Producer {
	T getItem();
}
