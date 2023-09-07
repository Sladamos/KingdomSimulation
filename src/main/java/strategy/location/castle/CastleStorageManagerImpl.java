package strategy.location.castle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.item.organism.human.Adult;
import strategy.item.present.NecklacePresent;
import strategy.item.present.RingPresent;
import strategy.item.statistic.Happiness;
import strategy.storage.OneItemStorage;

@AllArgsConstructor
@Getter
public class CastleStorageManagerImpl implements CastleStorageManager {

    private final OneItemStorage<NecklacePresent> necklacePresentStorage;

    private final OneItemStorage<RingPresent> ringPresentStorage;

    private final OneItemStorage<Adult> adultStorage;

    private final OneItemStorage<Happiness> happinessStorage;
}
