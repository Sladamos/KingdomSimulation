package strategy.location.village;

import strategy.producer.building.farm.Farm;
import strategy.producer.building.farm.WheatFarm;
import strategy.producer.building.livestock.Apiary;
import strategy.producer.building.livestock.Cow;
import strategy.producer.building.livestock.LivestockAnimal;
import strategy.producer.building.lumberjack.Lumberjack;
import strategy.producer.building.lumberjack.MahoganyLumberjack;
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

	public SarraxVillage() {
		cow = new Cow(4);
		apiary = new Apiary(2);
		farm = new WheatFarm(null, 12);
		lumberjack = new MahoganyLumberjack(14);
	}

	public void setWaterProducer(Supplier<Water> waterProducer) {
		farm.setProducer(waterProducer);
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
