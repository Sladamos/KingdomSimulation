package strategy.producer.building.miner.basic;

import strategy.item.mineral.gem.Sapphire;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class SapphireMiner extends Miner<Sapphire> {

	public SapphireMiner(OneItemStorage<Sapphire> destinationStorage, ProducerConfig producerConfig) {
		super(destinationStorage, producerConfig);
	}

	@Override
	protected Sapphire createNewItem() {
		return new Sapphire();
	}
}
