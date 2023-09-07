package strategy.kingdom;

import strategy.location.mountain.MountainStorageManager;
import strategy.location.village.VillageStorageManager;

public interface KingdomStorageManager {
    VillageStorageManager getVillageStorageManager();
    MountainStorageManager getMountainStorageManager();
}
