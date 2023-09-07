package strategy.location.village;

import strategy.producer.building.farm.Farm;
import strategy.producer.building.farm.WheatFarm;
import strategy.producer.building.livestock.Apiary;
import strategy.producer.building.livestock.Cow;
import strategy.producer.building.livestock.LivestockAnimal;
import strategy.producer.building.lumberjack.Lumberjack;
import strategy.producer.building.lumberjack.MahoganyLumberjack;
import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.plant.Wheat;
import strategy.item.wood.Mahogany;
import strategy.item.fluid.Water;

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
		cow = new Cow(0);
		apiary = new Apiary(0);
		farm = new WheatFarm(null, 0);
		lumberjack = new MahoganyLumberjack(0);
		executorService = Executors.newFixedThreadPool(4);
	}

	public void setWaterProducer(Supplier<Water> waterProducer) {
		farm.setProducer(waterProducer);
	}

	public Wheat getWheat() {
		return farm.getPlant();
	}

	public Milk getMilk() {
		return cow.getFood();
	}

	public Honey getHoney() {
		return apiary.getFood();
	}

	public Mahogany getWood() {
		return lumberjack.getWood();
	}

	@Override
	public void run() {
		executorService.execute(apiary);
		executorService.execute(cow);
		executorService.execute(farm);
		executorService.execute(lumberjack);
	}

	@Override
	public void terminate() {
		apiary.terminate();
		cow.terminate();
		farm.terminate();
		lumberjack.terminate();
		executorService.shutdownNow();
	}
}
