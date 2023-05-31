package strategy.producer;

public interface OneItemProducer<T> extends Producer {
	T getItem();
}
