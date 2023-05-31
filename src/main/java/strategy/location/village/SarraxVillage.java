package strategy.location.village;

import strategy.building.producer.farm.Farm;
import strategy.building.producer.farm.WheatFarm;
import strategy.building.producer.livestock.Apiary;
import strategy.building.producer.livestock.Cow;
import strategy.building.producer.livestock.LivestockAnimal;
import strategy.building.producer.lumberjack.Lumberjack;
import strategy.building.producer.lumberjack.MahoganyLumberjack;
import strategy.material.food.Honey;
import strategy.material.food.Milk;
import strategy.material.plant.Wheat;
import strategy.material.wood.Mahogany;
import strategy.product.fluid.Water;

import java.util.function.Supplier;

public class SarraxVillage implements Village, Runnable {

	private final Farm<Water, Wheat> farm;

	private final LivestockAnimal<Honey> apiary;

	private final LivestockAnimal<Milk> cow;

	private final Lumberjack<Mahogany> lumberjack;

	public SarraxVillage(Supplier<Water> waterSupplier) {
		cow = new Cow(4);
		apiary = new Apiary(2);
		farm = new WheatFarm(waterSupplier, 12);
		lumberjack = new MahoganyLumberjack(14);
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

	public synchronized Mahogany getWood() {
		return lumberjack.getWood();
	}

	@Override
	public void run() {
		Thread thread = new Thread(apiary);
		thread.start();
		thread = new Thread(cow);
		thread.start();
		thread = new Thread(farm);
		thread.start();
		thread = new Thread(lumberjack);
		thread.start();
	}
}
