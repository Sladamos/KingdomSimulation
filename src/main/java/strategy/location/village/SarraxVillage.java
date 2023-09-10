package strategy.location.village;

import strategy.item.fluid.Water;
import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.plant.Wheat;
import strategy.item.wood.Mahogany;
import strategy.message.JSONMessage;
import strategy.producer.building.farm.Farm;
import strategy.producer.building.farm.WheatFarm;
import strategy.producer.building.livestock.Apiary;
import strategy.producer.building.livestock.Cow;
import strategy.producer.building.livestock.LivestockAnimal;
import strategy.producer.building.lumberjack.Lumberjack;
import strategy.producer.building.lumberjack.MahoganyLumberjack;
import strategy.storage.OneItemStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxVillage implements Village {

	private final Farm<Water, Wheat> farm;

	private final LivestockAnimal<Honey> apiary;

	private final LivestockAnimal<Milk> cow;

	private final Lumberjack<Mahogany> lumberjack;

	private final ExecutorService executorService;

	public SarraxVillage(VillageStorageManager villageStorageManager, OneItemStorage<Water> waterStorage,
						 VillageConfig villageConfig) {
		cow = new Cow(villageStorageManager.getMilkStorage(), villageConfig.getCowConfig());
		apiary = new Apiary(villageStorageManager.getHoneyStorage(), villageConfig.getApiaryConfig());
		farm = new WheatFarm(waterStorage, villageStorageManager.getWheatStorage(), villageConfig.getFarmConfig());
		lumberjack = new MahoganyLumberjack(villageStorageManager.getMahoganyStorage(), villageConfig.getLumberjackConfig());
		executorService = Executors.newFixedThreadPool(4);
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

	private void onMessageReceived(JSONMessage message) {
		message.put("sender", "village");
		//invoke
	}
}
