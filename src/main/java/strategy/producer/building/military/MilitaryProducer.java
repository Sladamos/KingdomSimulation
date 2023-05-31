package strategy.producer.building.military;

import strategy.producer.Producer;
import strategy.military.MilitaryUnit;
import strategy.producer.building.Building;

public interface MilitaryProducer<T extends MilitaryUnit> extends Producer, Building {
	T getMilitaryUnit();
}
