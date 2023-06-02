package strategy.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class TwoToTwoProducer<T extends OneItemProducer<V>, U extends OneItemProducer<W>, V, W> implements Producer {

	private final T firstProducer;

	private final U secondProducer;

	private final ExecutorService service;

	public TwoToTwoProducer(T firstProducer, U secondProducer) {
		this.firstProducer = firstProducer;
		this.secondProducer = secondProducer;
		service = Executors.newFixedThreadPool(2);
	}

	@Override
	public void run() {
		service.execute(firstProducer);
		service.execute(secondProducer);
	}

	@Override
	public synchronized boolean isDestroyed() {
		return firstProducer.isDestroyed() && secondProducer.isDestroyed();
	}

	// @throws - BuildingDestroyedException if two producers are destroyed
	@Override
	public synchronized void dealDamage(int damage) {
		if(secondProducer.isDestroyed() || firstProducer.isDestroyed()) {
			dealDamageIfOneProducerIsDestroyed(damage);
		} else {
			dealDamageForBothProducers(damage);
		}
	}

	@Override
	public synchronized int getDurability() {
		return firstProducer.getDurability() + secondProducer.getDurability();
	}

	@Override
	public void terminate() {
		firstProducer.terminate();
		secondProducer.terminate();
	}

	private void dealDamageIfOneProducerIsDestroyed(int damage) {
		if(firstProducer.isDestroyed()) {
			secondProducer.dealDamage(damage);
		} else if (secondProducer.isDestroyed()) {
			firstProducer.dealDamage(damage);
		}
	}

	private void dealDamageForBothProducers(int damage) {

		int damageForIronProducer = Math.min(firstProducer.getDurability(), damage / 2);
		int damageForRubyProducer = Math.min(secondProducer.getDurability(), damage / 2);

		firstProducer.dealDamage(damageForIronProducer);
		secondProducer.dealDamage(damageForRubyProducer);
	}

	public synchronized V getFirstItem() {
		return firstProducer.getItem();
	}

	public synchronized W getSecondItem() {
		return secondProducer.getItem();
	}
}
