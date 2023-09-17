package strategy.producer.building.well.advanced;

import strategy.item.coin.GoldenCoin;
import strategy.item.fluid.Water;
import strategy.producer.TwoToTwoProducer;
import strategy.producer.building.well.basic.GoldenCoinWell;
import strategy.producer.building.well.basic.WaterWell;

public class SarraxWell extends TwoToTwoProducer<WaterWell, GoldenCoinWell, Water, GoldenCoin> {

	public SarraxWell(WaterWell firstProducer, GoldenCoinWell secondProducer) {
		super(firstProducer, secondProducer);
	}
}
