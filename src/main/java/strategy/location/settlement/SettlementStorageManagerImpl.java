package strategy.location.settlement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.bar.IronBar;
import strategy.item.coin.GoldenCoin;
import strategy.item.elixir.GrowthElixir;
import strategy.item.flour.WheatFlour;
import strategy.item.fluid.Water;
import strategy.item.food.baking.bread.WheatBread;
import strategy.item.jewellery.necklace.RubyNecklace;
import strategy.item.jewellery.ring.SapphireRing;
import strategy.item.military.infantry.HumanInfantryUnit;
import strategy.item.organism.human.Child;
import strategy.item.tool.bucket.IronBucket;
import strategy.item.tool.bucket.WoodenBucket;
import strategy.item.weapon.meele.sword.IronSword;
import strategy.storage.OneItemStorage;

@AllArgsConstructor
@Getter
public class SettlementStorageManagerImpl<T extends HumanInfantryUnit> implements SettlementStorageManager<T> {

    private final OneItemStorage<IronBar> ironBarStorage;

    private final OneItemStorage<IronSword> ironSwordStorage;

    private final OneItemStorage<IronBucket> ironBucketStorage;

    private final OneItemStorage<WoodenBucket> woodenBucketStorage;

    private final OneItemStorage<Water> waterStorage;

    private final OneItemStorage<GoldenCoin> goldenCoinStorage;

    private final OneItemStorage<WheatFlour> wheatFlourStorage;

    private final OneItemStorage<WheatBread> wheatBreadStorage;

    private final OneItemStorage<SapphireRing> sapphireRingStorage;

    private final OneItemStorage<RubyNecklace> rubyNecklaceStorage;

    private final OneItemStorage<GrowthElixir> growthElixirStorage;

    private final OneItemStorage<Child> childStorage;

    private final OneItemStorage<T> infantryUnitStorage;
}
