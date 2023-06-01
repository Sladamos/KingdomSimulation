package strategy.producer.royal.king;

import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.craftsman.present.PresentCraftsman;
import strategy.producer.building.jewellery.basic.Jeweller;
import strategy.producer.exceptions.ProducerDestroyedException;
import strategy.product.present.Present;


public abstract class King <T extends PresentCraftsman<?, U>, S extends PresentCraftsman<?, V>, U extends Present, V extends Present> extends TwoToTwoProducer<T, S, U, V> {

	public King(T firstProducer, S secondProducer) {
		super(firstProducer, secondProducer);
	}

	public synchronized U getFirstPresent() {
		return getFirstItem();
	}

	public synchronized V getSecondPresent() {
		return getSecondItem();
	}
}