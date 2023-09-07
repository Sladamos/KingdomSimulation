package strategy.location.mountain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.gem.Sapphire;
import strategy.item.mineral.ore.IronOre;
import strategy.storage.OneItemStorage;

@Getter
@AllArgsConstructor
public class MountainStorageManagerImpl implements MountainStorageManager {

    private final OneItemStorage<Salt> saltStorage;

    private final OneItemStorage<IronOre> ironOreStorage;

    private final OneItemStorage<Ruby> rubyStorage;

    private final OneItemStorage<Sapphire> sapphireStorage;

}
