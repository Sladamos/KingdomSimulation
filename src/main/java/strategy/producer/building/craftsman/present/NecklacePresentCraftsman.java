package strategy.producer.building.craftsman.present;

import strategy.item.jewellery.necklace.Necklace;
import strategy.item.present.NecklacePresent;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class NecklacePresentCraftsman<T extends Necklace> extends PresentCraftsman<T, NecklacePresent> {

	public NecklacePresentCraftsman(OneItemStorage<T> sourceStorage, OneItemStorage<NecklacePresent> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected NecklacePresent createNewItem(T material) {
		return new NecklacePresent();
	}
}
