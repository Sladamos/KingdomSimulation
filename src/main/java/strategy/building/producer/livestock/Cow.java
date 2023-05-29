package strategy.building.producer.livestock;

import strategy.material.food.Milk;

public class Cow extends LivestockAnimal<Milk> {

	private static final int COW_DURABILITY = 80;

	private static final int COW_PRODUCING_SPEED = 4;

	public Cow(int defaultStorageSize) {
		super(defaultStorageSize, COW_PRODUCING_SPEED, COW_DURABILITY);
	}

	@Override
	protected Milk createNewMaterial() {
		return new Milk();
	}
}
