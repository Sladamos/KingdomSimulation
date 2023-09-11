package strategy.location.mountain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.gem.Sapphire;
import strategy.item.mineral.ore.IronOre;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.storage.OneItemStorage;

@Getter
@AllArgsConstructor
public class MountainStorageManagerImpl implements MountainStorageManager {

    private final OneItemStorage<Salt> saltStorage;

    private final OneItemStorage<IronOre> ironOreStorage;

    private final OneItemStorage<Ruby> rubyStorage;

    private final OneItemStorage<Sapphire> sapphireStorage;

    @Override
    public void enableAcceptingItems() {
        saltStorage.enableAcceptingItems();
        ironOreStorage.enableAcceptingItems();
        rubyStorage.enableAcceptingItems();
        sapphireStorage.enableAcceptingItems();
    }

    @Override
    public void disableAcceptingItems() {
        saltStorage.disableAcceptingItems();
        ironOreStorage.disableAcceptingItems();
        rubyStorage.disableAcceptingItems();
        sapphireStorage.disableAcceptingItems();
    }

    @Override
    public MessagesNotifier<JSONMessage> getStorageMessagesNotifier() {
        MountainMessagesNotifier messagesNotifier = new MountainMessagesNotifier();
        return messagesNotifier;
    }
}
