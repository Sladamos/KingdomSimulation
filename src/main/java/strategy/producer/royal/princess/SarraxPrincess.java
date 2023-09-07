package strategy.producer.royal.princess;

import strategy.item.present.Present;
import strategy.item.statistic.Happiness;
import strategy.producer.ProducerConfig;
import strategy.producer.TwoToOneProducer;
import strategy.storage.OneItemStorage;

public class SarraxPrincess<T extends Present, S extends Present> extends TwoToOneProducer<T, S, Happiness> implements Princess {

    public SarraxPrincess(OneItemStorage<T> firstSourceStorage, OneItemStorage<S> secondSourceStorage,
                          OneItemStorage<Happiness> destinationStorage, ProducerConfig producerConfig) {
        super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected Happiness createNewItem(T material, S secondMaterial) {
        return new Happiness();
    }
}
