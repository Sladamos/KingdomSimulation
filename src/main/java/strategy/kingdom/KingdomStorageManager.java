package strategy.kingdom;

import strategy.location.castle.CastleStorageManager;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.village.VillageStorageManager;

public interface KingdomStorageManager {
    VillageStorageManager getVillageStorageManager();
    MountainStorageManager getMountainStorageManager();
    CastleStorageManager getCastleStorageManager();
}
