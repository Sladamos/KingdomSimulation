package strategy.producer.building.mill;

import strategy.item.flour.WheatFlour;
import strategy.item.plant.Wheat;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WheatMill extends Mill<Wheat, WheatFlour> {
    public WheatMill(OneItemStorage<Wheat> sourceStorage, OneItemStorage<WheatFlour> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected WheatFlour createNewItem(Wheat plant) {
        return new WheatFlour();
    }
}
