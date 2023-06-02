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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class SarraxVillage implements Village {

	private final Farm<Water, Wheat> farm;

	private final LivestockAnimal<Honey> apiary;

	private final LivestockAnimal<Milk> cow;

	private final Lumberjack<Mahogany> lumberjack;

	private final ExecutorService executorService;

	public SarraxVillage() {
		cow = new Cow(4);
		apiary = new Apiary(2);
		farm = new WheatFarm(null, 12);
		lumberjack = new MahoganyLumberjack(14);
		executorService = Executors.newFixedThreadPool(4);
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
		executorService.execute(apiary);
		executorService.execute(cow);
		executorService.execute(farm);
		executorService.execute(lumberjack);
	}
}
