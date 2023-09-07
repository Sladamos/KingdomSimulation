package strategy.location.settlement;

import strategy.item.bar.IronBar;
import strategy.item.coin.GoldenCoin;
import strategy.item.elixir.GrowthElixir;
import strategy.item.flour.WheatFlour;
import strategy.item.fluid.Water;
import strategy.item.food.baking.bread.WheatBread;
import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.military.infantry.InfantryUnit;
import strategy.item.organism.human.Child;
import strategy.item.tool.bucket.IronBucket;
import strategy.item.tool.bucket.WoodenBucket;
import strategy.item.weapon.meele.sword.IronSword;
import strategy.storage.OneItemStorage;

public interface SettlementStorageManager<T extends InfantryUnit> {
    OneItemStorage<IronBar> getIronBarStorage();
    OneItemStorage<IronSword> getIronSwordStorage();
    OneItemStorage<IronBucket> getIronBucketStorage();
    OneItemStorage<WoodenBucket> getWoodenBucketStorage();
    OneItemStorage<Water> getWaterStorage();
    OneItemStorage<GoldenCoin> getGoldenCoinStorage();
    OneItemStorage<WheatFlour> getWheatFlourStorage();
    OneItemStorage<WheatBread> getWheatBreadStorage();
    OneItemStorage<SapphireRing> getSapphireRingStorage();
    OneItemStorage<RubyNecklace> getRubyNecklaceStorage();
    OneItemStorage<GrowthElixir> getGrowthElixirStorage();
    OneItemStorage<Child> getChildStorage();
    OneItemStorage<T> getInfantryUnitStorage();
}
