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
import strategy.item.military.infantry.InfantryUnit;
import strategy.item.organism.human.Child;
import strategy.item.tool.bucket.IronBucket;
import strategy.item.tool.bucket.WoodenBucket;
import strategy.item.weapon.meele.sword.IronSword;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.storage.OneItemStorage;

@AllArgsConstructor
@Getter
public class SettlementStorageManagerImpl implements SettlementStorageManager {

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

    private final OneItemStorage<InfantryUnit> infantryUnitStorage;

    @Override
    public void enableAcceptingItems() {
        ironBarStorage.enableAcceptingItems();
        ironSwordStorage.enableAcceptingItems();
        ironBucketStorage.enableAcceptingItems();
        woodenBucketStorage.enableAcceptingItems();
        waterStorage.enableAcceptingItems();
        goldenCoinStorage.enableAcceptingItems();
        wheatFlourStorage.enableAcceptingItems();
        wheatBreadStorage.enableAcceptingItems();
        sapphireRingStorage.enableAcceptingItems();
        rubyNecklaceStorage.enableAcceptingItems();
        growthElixirStorage.enableAcceptingItems();
        childStorage.enableAcceptingItems();
        infantryUnitStorage.enableAcceptingItems();
    }

    @Override
    public void disableAcceptingItems() {
        ironBarStorage.disableAcceptingItems();
        ironSwordStorage.disableAcceptingItems();
        ironBucketStorage.disableAcceptingItems();
        woodenBucketStorage.disableAcceptingItems();
        waterStorage.disableAcceptingItems();
        goldenCoinStorage.disableAcceptingItems();
        wheatFlourStorage.disableAcceptingItems();
        wheatBreadStorage.disableAcceptingItems();
        sapphireRingStorage.disableAcceptingItems();
        rubyNecklaceStorage.disableAcceptingItems();
        growthElixirStorage.disableAcceptingItems();
        childStorage.disableAcceptingItems();
        infantryUnitStorage.disableAcceptingItems();
    }

    @Override
    public MessagesNotifier<JSONMessage> getStorageMessagesNotifier() {
        SettlementMessagesNotifier messagesNotifier = new SettlementMessagesNotifier();
        ironBarStorage.addListener(messagesNotifier);
        ironSwordStorage.addListener(messagesNotifier);
        ironBucketStorage.addListener(messagesNotifier);
        woodenBucketStorage.addListener(messagesNotifier);
        waterStorage.addListener(messagesNotifier);
        goldenCoinStorage.addListener(messagesNotifier);
        wheatFlourStorage.addListener(messagesNotifier);
        wheatBreadStorage.addListener(messagesNotifier);
        sapphireRingStorage.addListener(messagesNotifier);
        rubyNecklaceStorage.addListener(messagesNotifier);
        growthElixirStorage.addListener(messagesNotifier);
        childStorage.addListener(messagesNotifier);
        infantryUnitStorage.addListener(messagesNotifier);
        return messagesNotifier;
    }
}
