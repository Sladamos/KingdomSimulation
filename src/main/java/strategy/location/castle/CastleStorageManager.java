package strategy.location.castle;

import strategy.StorageManager;
import strategy.item.organism.human.Adult;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;
import strategy.item.statistic.Happiness;
import strategy.storage.OneItemStorage;

public interface CastleStorageManager extends StorageManager {
    OneItemStorage<NecklacePresent> getNecklacePresentStorage();
    OneItemStorage<RingPresent> getRingPresentStorage();
    OneItemStorage<Adult> getAdultStorage();
    OneItemStorage<Happiness> getHappinessStorage();
}
