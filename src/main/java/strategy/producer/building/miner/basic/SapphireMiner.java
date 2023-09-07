package strategy.producer.building.miner.basic;

import strategy.item.mineral.gem.Sapphire;

public class SapphireMiner extends Miner<Sapphire> {

	private static final int SAPPHIRE_MINER_DURABILITY = 40;

	private static final int SAPPHIRE_MINER_MINING_SPEED = 3;

	public SapphireMiner(int defaultStorageSize) {
		super(defaultStorageSize, SAPPHIRE_MINER_MINING_SPEED, SAPPHIRE_MINER_DURABILITY);
	}

	@Override
	protected Sapphire produceNewItem() {
		return new Sapphire();
	}
}
