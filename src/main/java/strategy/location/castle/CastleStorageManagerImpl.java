package strategy.location.castle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.organism.human.Adult;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;
import strategy.item.statistic.Happiness;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.storage.OneItemStorage;

@AllArgsConstructor
@Getter
public class CastleStorageManagerImpl implements CastleStorageManager {

    private final OneItemStorage<NecklacePresent> necklacePresentStorage;

    private final OneItemStorage<RingPresent> ringPresentStorage;

    private final OneItemStorage<Adult> adultStorage;

    private final OneItemStorage<Happiness> happinessStorage;

    @Override
    public void enableAcceptingItems() {
        necklacePresentStorage.enableAcceptingItems();
        ringPresentStorage.enableAcceptingItems();
        adultStorage.enableAcceptingItems();
        happinessStorage.enableAcceptingItems();
    }

    @Override
    public void disableAcceptingItems() {
        necklacePresentStorage.disableAcceptingItems();
        ringPresentStorage.disableAcceptingItems();
        adultStorage.disableAcceptingItems();
        happinessStorage.disableAcceptingItems();
    }

    @Override
    public MessagesNotifier<JSONMessage> getStorageMessagesNotifier() {
        CastleMessagesNotifier messagesNotifier = new CastleMessagesNotifier();
        necklacePresentStorage.addListener(messagesNotifier);
        ringPresentStorage.addListener(messagesNotifier);
        adultStorage.addListener(messagesNotifier);
        happinessStorage.addListener(messagesNotifier);
        return messagesNotifier;
    }
}
