package strategy.location.village;

import strategy.building.producer.farm.WheatFarm;
import strategy.building.producer.livestock.Apiary;
import strategy.building.producer.livestock.Cow;
import strategy.material.food.Honey;
import strategy.material.food.Milk;
import strategy.material.plant.Wheat;
import strategy.product.fluid.Water;

import java.util.function.Supplier;

public class SarraxVillage implements Village {

	private final WheatFarm farm;

	private final Apiary apiary;

	private final Cow cow;

	public SarraxVillage(Supplier<Water> waterSupplier) {
		cow = new Cow(4);
		apiary = new Apiary(2);
		farm = new WheatFarm(waterSupplier, 12);
	}

	public synchronized Wheat getWheat() {
		return farm.getPlant();
	}

	public synchronized Milk getMilk() {
		return cow.getFood();
	}

	public synchronized Honey getHoney() {
		return apiary.getFood();
	}
}
