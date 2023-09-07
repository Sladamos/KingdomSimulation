package strategy.producer.building.craftsman.present;

import strategy.item.jewellery.necklace.Necklace;
import strategy.item.present.NecklacePresent;
import strategy.producer.ProducerConfig;

import java.util.function.Supplier;

public class NecklacePresentCraftsman<T extends Necklace> extends PresentCraftsman<T, NecklacePresent> {

	public NecklacePresentCraftsman(Supplier<T> producer, ProducerConfig producerConfig) {
		super(producer, producerConfig);
	}

	@Override
	protected NecklacePresent createNewItem(T material) {
		return new NecklacePresent();
	}
}
