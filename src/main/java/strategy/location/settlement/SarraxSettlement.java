package strategy.location.settlement;

import strategy.building.producer.alchemist.GrowthElixirAlchemist;
import strategy.building.producer.artisan.bucket.IronBucketArtisan;
import strategy.building.producer.artisan.bucket.WoodenBucketArtisan;
import strategy.building.producer.bakery.bread.WheatBreadBakery;
import strategy.building.producer.human.ChildHouse;
import strategy.building.producer.jewellery.advanced.SarraxJeweller;
import strategy.building.producer.military.infantry.WarriorBarracks;
import strategy.building.producer.mill.WheatMill;
import strategy.building.producer.smelter.IronBarSmelter;
import strategy.building.producer.smith.meele.IronSwordBlacksmith;
import strategy.building.producer.well.advanced.SarraxWell;
import strategy.location.mountain.SarraxMountain;
import strategy.product.coin.GoldenCoin;
import strategy.product.food.baking.bread.WheatBread;
import strategy.product.weapon.meele.sword.IronSword;

public class SarraxSettlement implements Settlement {

	private final IronSwordBlacksmith blacksmith;

	private final IronBucketArtisan ironBucketArtisan;

	private final WoodenBucketArtisan woodenBucketArtisan;

	private final IronBarSmelter ironBarSmelter;

	private final SarraxWell well;

	private final WheatMill wheatMill;

	private final WheatBreadBakery bakery;

	private final SarraxJeweller jeweller;

	private final GrowthElixirAlchemist alchemist;

	private final ChildHouse<GoldenCoin, WheatBread> childHouse;

	private final WarriorBarracks<IronSword> barracks;

	public SarraxSettlement(SarraxMountain mountain) {
		ironBarSmelter = new IronBarSmelter(mountain::getIronOre, 13);
	}
}
