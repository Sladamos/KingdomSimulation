package strategy.location.settlement;

import strategy.building.producer.alchemist.Alchemist;
import strategy.building.producer.alchemist.GrowthElixirAlchemist;
import strategy.building.producer.artisan.Artisan;
import strategy.building.producer.artisan.bucket.IronBucketArtisan;
import strategy.building.producer.artisan.bucket.WoodenBucketArtisan;
import strategy.building.producer.bakery.Bakery;
import strategy.building.producer.bakery.bread.WheatBreadBakery;
import strategy.building.producer.human.ChildHouse;
import strategy.building.producer.jewellery.advanced.SarraxJeweller;
import strategy.building.producer.military.infantry.WarriorBarracks;
import strategy.building.producer.mill.Mill;
import strategy.building.producer.mill.WheatMill;
import strategy.building.producer.smelter.IronBarSmelter;
import strategy.building.producer.smelter.Smelter;
import strategy.building.producer.smith.meele.Blacksmith;
import strategy.building.producer.smith.meele.IronSwordBlacksmith;
import strategy.building.producer.well.advanced.SarraxWell;
import strategy.location.mountain.SarraxMountain;
import strategy.location.village.SarraxVillage;
import strategy.material.bar.IronBar;
import strategy.material.food.Honey;
import strategy.material.food.Milk;
import strategy.material.mineral.Salt;
import strategy.material.mineral.ore.IronOre;
import strategy.material.plant.Wheat;
import strategy.material.wood.Mahogany;
import strategy.product.coin.GoldenCoin;
import strategy.product.elixir.GrowthElixir;
import strategy.product.flour.WheatFlour;
import strategy.product.food.baking.bread.WheatBread;
import strategy.product.tool.bucket.IronBucket;
import strategy.product.tool.bucket.WoodenBucket;
import strategy.product.weapon.meele.sword.IronSword;

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

	public SarraxSettlement(SarraxMountain mountain, SarraxVillage village) {
		ironBarSmelter = new IronBarSmelter(mountain::getIronOre, 13);
		blacksmith = new IronSwordBlacksmith(ironBarSmelter::getBar, 2);
		barracks = new WarriorBarracks<>(null, blacksmith::getWeapon, 0);
		//TODO: implement castle

		ironBucketArtisan = new IronBucketArtisan(ironBarSmelter::getBar, 2);
		woodenBucketArtisan = new WoodenBucketArtisan(village::getWood, 2);
		well = new SarraxWell(woodenBucketArtisan::getTool, ironBucketArtisan::getTool);

		wheatMill = new WheatMill(village::getWheat, 1);
		bakery = new WheatBreadBakery(wheatMill::getFlour, mountain::getSalt, 3);
		childHouse = new ChildHouse<>(well::getGoldenCoin, bakery::getBaking, 0);

		jeweller = new SarraxJeweller(mountain::getRuby, mountain::getSapphire);
		alchemist = new GrowthElixirAlchemist(village::getMilk, village::getHoney, 2);
	}
}
