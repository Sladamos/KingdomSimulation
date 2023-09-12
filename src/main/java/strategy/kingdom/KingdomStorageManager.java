package strategy.kingdom;

import strategy.StorageManager;
import strategy.item.military.infantry.InfantryUnit;
import strategy.location.castle.CastleStorageManager;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.settlement.SettlementStorageManager;
import strategy.location.village.VillageStorageManager;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;

public interface KingdomStorageManager extends StorageManager {
    VillageStorageManager getVillageStorageManager();
    MountainStorageManager getMountainStorageManager();
    SettlementStorageManager getSettlementStorageManager();
    CastleStorageManager getCastleStorageManager();
    MessagesNotifier<JSONMessage> getStorageMessagesNotifier();
}
