package strategy.producer.building.miner.basic;

import strategy.item.mineral.Salt;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class SaltMiner extends Miner<Salt> {

	public SaltMiner(OneItemStorage<Salt> destinationStorage, ProducerConfig producerConfig) {
		super(destinationStorage, producerConfig);
	}

	@Override
	protected Salt createNewItem() {
		return new Salt();
	}
}
