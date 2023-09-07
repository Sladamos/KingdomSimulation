package strategy.kingdom;

import lombok.Getter;
import strategy.location.castle.CastleStorageManager;
import strategy.location.castle.CastleStorageManagerImpl;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.mountain.MountainStorageManagerImpl;
import strategy.location.village.VillageStorageManager;
import strategy.location.village.VillageStorageManagerImpl;
import strategy.storage.UnlimitedOneItemStorage;

@Getter
public class SarraxKingdomStorageManager implements KingdomStorageManager {

    private final MountainStorageManager mountainStorageManager;

    private final VillageStorageManager villageStorageManager;

    private final CastleStorageManager castleStorageManager;

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
    }
}
