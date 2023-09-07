package strategy.kingdom;

import strategy.StorageManager;
import strategy.item.military.infantry.InfantryUnit;
import strategy.location.castle.CastleStorageManager;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.settlement.SettlementStorageManager;
import strategy.location.village.VillageStorageManager;

public interface KingdomStorageManager<T extends InfantryUnit> extends StorageManager {
    VillageStorageManager getVillageStorageManager();
    MountainStorageManager getMountainStorageManager();
    SettlementStorageManager<T> getSettlementStorageManager();
    CastleStorageManager getCastleStorageManager();
}
