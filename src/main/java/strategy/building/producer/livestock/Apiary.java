package strategy.building.producer.livestock;

import strategy.material.food.Honey;

public class Apiary extends LivestockAnimal<Honey> {

	private static final int APIARY_DURABILITY = 40;

	private static final int APIARY_PRODUCING_SPEED = 2;

	public Apiary(int defaultStorageSize) {
		super(defaultStorageSize, APIARY_PRODUCING_SPEED, APIARY_DURABILITY);
	}

	@Override
	protected Honey produceNewItem() {
		return new Honey();
	}
}
