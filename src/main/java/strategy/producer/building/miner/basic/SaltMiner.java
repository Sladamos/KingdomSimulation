package strategy.producer.building.miner.basic;

import strategy.material.mineral.Salt;

public class SaltMiner extends Miner<Salt> {

	private static final int SALT_MINER_DURABILITY = 200;

	private static final int SALT_MINER_MINING_SPEED = 4;

	public SaltMiner(int defaultStorageSize) {
		super(defaultStorageSize, SALT_MINER_MINING_SPEED, SALT_MINER_DURABILITY);
	}

	@Override
	protected Salt produceNewItem() {
		return new Salt();
	}
}
