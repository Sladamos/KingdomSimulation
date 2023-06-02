package strategy.location.settlement;

import strategy.organism.human.Child;
import strategy.producer.building.alchemist.Alchemist;
import strategy.producer.building.alchemist.GrowthElixirAlchemist;
import strategy.producer.building.artisan.Artisan;
import strategy.producer.building.artisan.bucket.IronBucketArtisan;
import strategy.producer.building.artisan.bucket.WoodenBucketArtisan;
import strategy.producer.building.bakery.Bakery;
import strategy.producer.building.bakery.bread.WheatBreadBakery;
import strategy.producer.building.house.ChildHouse;
import strategy.producer.building.jewellery.advanced.SarraxJeweller;
import strategy.producer.building.military.infantry.WarriorBarracks;
import strategy.producer.building.mill.Mill;
import strategy.producer.building.mill.WheatMill;
import strategy.producer.building.smelter.IronBarSmelter;
import strategy.producer.building.smelter.Smelter;
import strategy.producer.building.smith.meele.Blacksmith;
import strategy.producer.building.smith.meele.IronSwordBlacksmith;
import strategy.producer.building.well.advanced.SarraxWell;
import strategy.location.mountain.SarraxMountain;
import strategy.location.village.SarraxVillage;
import strategy.material.bar.IronBar;
import strategy.material.food.Honey;
import strategy.material.food.Milk;
import strategy.material.mineral.Salt;
import strategy.material.mineral.ore.IronOre;
import strategy.material.plant.Wheat;
import strategy.material.wood.Mahogany;
import strategy.organism.human.Adult;
import strategy.product.coin.GoldenCoin;
import strategy.product.elixir.GrowthElixir;
import strategy.product.flour.WheatFlour;
import strategy.product.fluid.Water;
import strategy.product.food.baking.bread.WheatBread;
import strategy.product.jewellery.necklace.RubyNecklace;
import strategy.product.jewellery.ring.SapphireRing;
import strategy.product.tool.bucket.IronBucket;
import strategy.product.tool.bucket.WoodenBucket;
import strategy.product.weapon.meele.sword.IronSword;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

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

	public SarraxSettlement(SarraxMountain mountain, SarraxVillage village) {
		ironBarSmelter = new IronBarSmelter(mountain::getIronOre, 0);
		blacksmith = new IronSwordBlacksmith(ironBarSmelter::getBar, 0);
		barracks = new WarriorBarracks<>(null, blacksmith::getWeapon, 0);

		ironBucketArtisan = new IronBucketArtisan(ironBarSmelter::getBar, 0);
		woodenBucketArtisan = new WoodenBucketArtisan(village::getWood, 0);
		well = new SarraxWell(woodenBucketArtisan::getTool, ironBucketArtisan::getTool);

		wheatMill = new WheatMill(village::getWheat, 0);
		bakery = new WheatBreadBakery(wheatMill::getFlour, mountain::getSalt, 0);
		childHouse = new ChildHouse<>(well::getGoldenCoin, bakery::getBaking, 0);

		jeweller = new SarraxJeweller(mountain::getRuby, mountain::getSapphire);
		alchemist = new GrowthElixirAlchemist(village::getMilk, village::getHoney, 0);

		executorService = Executors.newFixedThreadPool(11);
	}

	public Water getWater() {
		return well.getWater();
	}

	public GrowthElixir getGrowthElixir() {
		return alchemist.getElixir();
	}

	public Child getChild() {
		return childHouse.getHuman();
	}

	public RubyNecklace getRubyNecklace() {
		return jeweller.getRubyNecklace();
	}

	public SapphireRing getSapphireRing() {
		return jeweller.getSapphireRing();
	}

	public void setAdultsProducer(Supplier<Adult> adultsProducer) {
		barracks.setFirstProducer(adultsProducer);
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
}
