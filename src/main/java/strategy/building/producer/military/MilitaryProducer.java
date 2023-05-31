package strategy.building.producer.military;

import strategy.building.producer.Producer;
import strategy.military.MilitaryUnit;

public interface MilitaryProducer<T extends MilitaryUnit> extends Producer {
	T getMilitaryUnit();
}
