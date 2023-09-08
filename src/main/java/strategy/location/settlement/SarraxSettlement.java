package strategy.location.settlement;

import strategy.item.bar.IronBar;
import strategy.item.coin.GoldenCoin;
import strategy.item.elixir.GrowthElixir;
import strategy.item.flour.WheatFlour;
import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.food.baking.bread.WheatBread;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.item.mineral.Salt;
import strategy.item.mineral.ore.IronOre;
import strategy.item.organism.human.Adult;
import strategy.item.plant.Wheat;
import strategy.item.tool.bucket.IronBucket;
import strategy.item.tool.bucket.WoodenBucket;
import strategy.item.weapon.meele.sword.IronSword;
import strategy.item.wood.Mahogany;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.village.VillageStorageManager;
import strategy.producer.building.alchemist.Alchemist;
import strategy.producer.building.alchemist.GrowthElixirAlchemist;
import strategy.producer.building.artisan.Artisan;
import strategy.producer.building.artisan.bucket.IronBucketArtisan;
import strategy.producer.building.artisan.bucket.WoodenBucketArtisan;
import strategy.producer.building.bakery.Bakery;
import strategy.producer.building.bakery.bread.WheatBreadBakery;
import strategy.producer.building.house.ChildHouse;
import strategy.producer.building.jewellery.advanced.SarraxJeweller;
import strategy.producer.building.jewellery.basic.necklace.RubyNecklaceJeweller;
import strategy.producer.building.jewellery.basic.ring.SapphireRingJeweller;
import strategy.producer.building.military.infantry.WarriorBarracks;
import strategy.producer.building.mill.Mill;
import strategy.producer.building.mill.WheatMill;
import strategy.producer.building.smelter.IronBarSmelter;
import strategy.producer.building.smelter.Smelter;
import strategy.producer.building.smith.meele.Blacksmith;
import strategy.producer.building.smith.meele.IronSwordBlacksmith;
import strategy.producer.building.well.advanced.SarraxWell;
import strategy.producer.building.well.basic.GoldenCoinWell;
import strategy.producer.building.well.basic.WaterWell;
import strategy.storage.OneItemStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxSettlement implements Settlement {

	private final Blacksmith<IronBar, IronSword> blacksmith;

	private final Artisan<IronBar, IronBucket> ironBucketArtisan;

	private final Artisan<Mahogany, WoodenBucket> woodenBucketArtisan;

	private final Smelter<IronOre, IronBar> ironBarSmelter;

	private final SarraxWell well;

	private final Mill<Wheat, WheatFlour> wheatMill;

	private final Bakery<WheatFlour, Salt, WheatBread> bakery;

	private final SarraxJeweller jeweller;

	private final Alchemist<Milk, Honey, GrowthElixir> alchemist;

	private final ChildHouse<GoldenCoin, WheatBread> childHouse;

	private final WarriorBarracks<IronSword> barracks;

	private final ExecutorService executorService;

	public SarraxSettlement(SettlementStorageManager<Warrior> settlementStorageManager,
							VillageStorageManager villageStorageManager,
							MountainStorageManager mountainStorageManager,
							OneItemStorage<Adult> adultStorage,
							SettlementConfig settlementConfig) {
		ironBarSmelter = new IronBarSmelter(mountainStorageManager.getIronOreStorage(),
				settlementStorageManager.getIronBarStorage(), settlementConfig.getSmelterConfig());

		blacksmith = new IronSwordBlacksmith(settlementStorageManager.getIronBarStorage(),
				settlementStorageManager.getIronSwordStorage(), settlementConfig.getBlacksmithConfig());

		barracks = new WarriorBarracks<>(adultStorage, settlementStorageManager.getIronSwordStorage(),
				settlementStorageManager.getInfantryUnitStorage(), settlementConfig.getBarracksConfig());

		ironBucketArtisan = new IronBucketArtisan(settlementStorageManager.getIronBarStorage(),
				settlementStorageManager.getIronBucketStorage(), settlementConfig.getIronBucketArtisanConfig());

		woodenBucketArtisan = new WoodenBucketArtisan(villageStorageManager.getMahoganyStorage(),
				settlementStorageManager.getWoodenBucketStorage(), settlementConfig.getWoodenBucketArtisanConfig());

		well = createSarraxWell(settlementStorageManager, settlementConfig);

		wheatMill = new WheatMill(villageStorageManager.getWheatStorage(),
				settlementStorageManager.getWheatFlourStorage(), settlementConfig.getMillConfig());

		bakery = new WheatBreadBakery(settlementStorageManager.getWheatFlourStorage(), mountainStorageManager.getSaltStorage(),
				settlementStorageManager.getWheatBreadStorage(), settlementConfig.getBakeryConfig());

		childHouse = new ChildHouse<>(settlementStorageManager.getGoldenCoinStorage(), settlementStorageManager.getWheatBreadStorage(),
				settlementStorageManager.getChildStorage(), settlementConfig.getChildHouseConfig());

		jeweller = createSarraxJeweller(mountainStorageManager, settlementStorageManager, settlementConfig);

		alchemist = new GrowthElixirAlchemist(villageStorageManager.getMilkStorage(), villageStorageManager.getHoneyStorage(),
				settlementStorageManager.getGrowthElixirStorage(), settlementConfig.getAlchemistConfig());

		executorService = Executors.newFixedThreadPool(11);
	}

	private SarraxWell createSarraxWell(SettlementStorageManager<Warrior> settlementStorageManager, SettlementConfig settlementConfig) {
		WaterWell waterWell = new WaterWell(settlementStorageManager.getIronBucketStorage(), settlementStorageManager.getWaterStorage(), settlementConfig.getWaterWellConfig());
		GoldenCoinWell goldenCoinWell = new GoldenCoinWell(settlementStorageManager.getWoodenBucketStorage(), settlementStorageManager.getGoldenCoinStorage(), settlementConfig.goldenCoinWellConfig);
		return new SarraxWell(waterWell, goldenCoinWell);
	}

	private SarraxJeweller createSarraxJeweller(MountainStorageManager mountainStorageManager,
												SettlementStorageManager<Warrior> settlementStorageManager, SettlementConfig settlementConfig) {
		SapphireRingJeweller sapphireRingJeweller = new SapphireRingJeweller(mountainStorageManager.getSapphireStorage(),
				settlementStorageManager.getSapphireRingStorage(), settlementConfig.getRingJewellerConfig());
		RubyNecklaceJeweller rubyNecklaceJeweller = new RubyNecklaceJeweller(mountainStorageManager.getRubyStorage(),
				settlementStorageManager.getRubyNecklaceStorage(), settlementConfig.getNecklaceJewellerConfig());
		return new SarraxJeweller(sapphireRingJeweller, rubyNecklaceJeweller);
	}

	@Override
	public void run() {
		executorService.execute(ironBarSmelter);
		executorService.execute(blacksmith);
		executorService.execute(barracks);
		executorService.execute(ironBucketArtisan);
		executorService.execute(woodenBucketArtisan);
		executorService.execute(well);
		executorService.execute(wheatMill);
		executorService.execute(bakery);
		executorService.execute(childHouse);
		executorService.execute(jeweller);
		executorService.execute(alchemist);
	}

	@Override
	public void terminate() {
		ironBarSmelter.terminate();
		blacksmith.terminate();
		barracks.terminate();
		ironBucketArtisan.terminate();
		woodenBucketArtisan.terminate();
		well.terminate();
		wheatMill.terminate();
		bakery.terminate();
		childHouse.terminate();
		jeweller.terminate();
		alchemist.terminate();
		executorService.shutdownNow();
	}
}
