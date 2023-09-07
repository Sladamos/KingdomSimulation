package strategy.producer.building.well.basic;

import strategy.item.coin.GoldenCoin;
import strategy.item.tool.bucket.WoodenBucket;
import strategy.producer.ProducerConfig;
import strategy.producer.building.well.Well;
import strategy.storage.OneItemStorage;

public class GoldenCoinWell extends Well<WoodenBucket, GoldenCoin> {

	public GoldenCoinWell(OneItemStorage<WoodenBucket> sourceStorage, OneItemStorage<GoldenCoin> destinationStorage, ProducerConfig producerConfig) {
		super(sourceStorage, destinationStorage, producerConfig);
	}

	@Override
	protected GoldenCoin createNewItem(WoodenBucket material) {
		return new GoldenCoin();
	}
}
