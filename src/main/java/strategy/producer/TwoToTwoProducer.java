package strategy.producer;

import strategy.item.Item;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class TwoToTwoProducer<T extends OneItemProducer<V>, U extends OneItemProducer<W>, V extends Item, W extends Item> implements Producer {

	private final T firstProducer;

	private final U secondProducer;

	private final ExecutorService executorService;

	public TwoToTwoProducer(T firstProducer, U secondProducer) {
		this.firstProducer = firstProducer;
		this.secondProducer = secondProducer;
		executorService = Executors.newFixedThreadPool(2);
	}

	@Override
	public void run() {
		executorService.execute(firstProducer);
		executorService.execute(secondProducer);
	}

	@Override
	public void terminate() {
		firstProducer.terminate();
		secondProducer.terminate();
		executorService.shutdownNow();
	}
}
