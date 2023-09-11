package strategy.location.village;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.food.Honey;
import strategy.item.food.Milk;
import strategy.item.plant.Wheat;
import strategy.item.wood.Mahogany;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.storage.OneItemStorage;

@AllArgsConstructor
@Getter
public class VillageStorageManagerImpl implements VillageStorageManager {

    private final OneItemStorage<Milk> milkStorage;

    private final OneItemStorage<Honey> honeyStorage;

    private final OneItemStorage<Wheat> wheatStorage;

    private final OneItemStorage<Mahogany> mahoganyStorage;

    @Override
    public void enableAcceptingItems() {
        milkStorage.enableAcceptingItems();
        honeyStorage.enableAcceptingItems();
        wheatStorage.enableAcceptingItems();
        mahoganyStorage.enableAcceptingItems();
    }

    @Override
    public void disableAcceptingItems() {
        milkStorage.disableAcceptingItems();
        honeyStorage.disableAcceptingItems();
        wheatStorage.disableAcceptingItems();
        mahoganyStorage.disableAcceptingItems();
    }

    @Override
    public MessagesNotifier<JSONMessage> getStorageMessagesNotifier() {
        VillageMessagesNotifier villageMessagesNotifier = new VillageMessagesNotifier();
        milkStorage.addListener(villageMessagesNotifier);
        honeyStorage.addListener(villageMessagesNotifier);
        wheatStorage.addListener(villageMessagesNotifier);
        mahoganyStorage.addListener(villageMessagesNotifier);
        return villageMessagesNotifier;
    }
}
