package strategy.location.mountain;

import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.gem.Sapphire;
import strategy.item.mineral.ore.IronOre;
import strategy.storage.OneItemStorage;

public interface MountainStorageManager {
    OneItemStorage<Salt> getSaltStorage();
    OneItemStorage<Sapphire> getSapphireStorage();
    OneItemStorage<Ruby> getRubyStorage();
    OneItemStorage<IronOre> getIronOreStorage();
}
