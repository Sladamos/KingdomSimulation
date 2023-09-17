package strategy.kingdom;

import lombok.Getter;
import strategy.location.castle.CastleStorageManager;
import strategy.location.castle.CastleStorageManagerImpl;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.mountain.MountainStorageManagerImpl;
import strategy.location.settlement.SettlementStorageManager;
import strategy.location.settlement.SettlementStorageManagerImpl;
import strategy.location.village.VillageStorageManager;
import strategy.location.village.VillageStorageManagerImpl;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.notifier.MessagesNotifierImpl;
import strategy.storage.UnlimitedOneItemStorage;

@Getter
public class SarraxKingdomStorageManager implements KingdomStorageManager {

    private final MountainStorageManager mountainStorageManager;

    private final VillageStorageManager villageStorageManager;

    private final CastleStorageManager castleStorageManager;

    private final SettlementStorageManager settlementStorageManager;

    public SarraxKingdomStorageManager() {
        mountainStorageManager = new MountainStorageManagerImpl(new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>());

        villageStorageManager = new VillageStorageManagerImpl(new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>());

        castleStorageManager = new CastleStorageManagerImpl(new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>());

        settlementStorageManager = new SettlementStorageManagerImpl(new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>(),
                new UnlimitedOneItemStorage<>());
    }

    @Override
    public void enableAcceptingItems() {
        mountainStorageManager.enableAcceptingItems();
        villageStorageManager.enableAcceptingItems();
        settlementStorageManager.enableAcceptingItems();
        castleStorageManager.enableAcceptingItems();
    }

    @Override
    public void disableAcceptingItems() {
        mountainStorageManager.disableAcceptingItems();
        villageStorageManager.disableAcceptingItems();
        settlementStorageManager.disableAcceptingItems();
        castleStorageManager.disableAcceptingItems();
    }

    @Override
    public MessagesNotifier<JSONMessage> getStorageMessagesNotifier() {
        MessagesNotifier<JSONMessage> notifier = new MessagesNotifierImpl<>();
        villageStorageManager.getStorageMessagesNotifier().addListener(notifier);
        mountainStorageManager.getStorageMessagesNotifier().addListener(notifier);
        settlementStorageManager.getStorageMessagesNotifier().addListener(notifier);
        castleStorageManager.getStorageMessagesNotifier().addListener(notifier);
        return notifier;
    }
}
