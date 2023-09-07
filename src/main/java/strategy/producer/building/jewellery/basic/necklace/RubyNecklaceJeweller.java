package strategy.producer.building.jewellery.basic.necklace;

import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.mineral.gem.Ruby;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class RubyNecklaceJeweller extends strategy.producer.building.jewellery.basic.necklace.NecklaceJeweller<Ruby, RubyNecklace> {

	public RubyNecklaceJeweller(OneItemStorage<Ruby> sourceStorage, OneItemStorage<RubyNecklace> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected RubyNecklace createNewItem(Ruby material) {
		return new RubyNecklace();
	}
}
